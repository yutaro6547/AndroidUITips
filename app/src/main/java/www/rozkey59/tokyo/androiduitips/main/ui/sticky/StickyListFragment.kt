package www.rozkey59.tokyo.androiduitips.main.ui.sticky

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.stickyheader.StickyHeaderLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.databinding.FragmentStickyListBinding
import www.rozkey59.tokyo.androiduitips.main.ui.model.StickyHeaderModel_
import www.rozkey59.tokyo.androiduitips.main.ui.model.stickyContents
import www.rozkey59.tokyo.androiduitips.main.ui.model.stickyHeader
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData
import java.lang.IllegalArgumentException
import javax.inject.Inject
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.LOADING
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.ERROR
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.IDEAL
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.BLANK
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.PARTIAL

@AndroidEntryPoint
class StickyListFragment: Fragment() {

    private lateinit var binding: FragmentStickyListBinding
    @Inject
    lateinit var viewModel: StickyListViewModel
    private lateinit var stickyHeaderController: TypedEpoxyController<List<ListData>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStickyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        stickyHeaderController = object : TypedEpoxyController<List<ListData>>() {
            override fun buildModels(data: List<ListData>) {
                data.forEach {
                    stickyHeader {
                        id(STICKY_HEADER_ID, "${it.id}Header")
                        titleText(it.name)
                        onTitleClicked { _ ->
                            Toast.makeText(requireContext(), "Press ${it.name}!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    stickyContents {
                        id(STICKY_CONTENTS_ID, "${it.id}")
                        listData(it)
                        onRootClickListener { _ ->
                            Toast.makeText(requireContext(), "Press ${it.description}!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun isStickyHeader(position: Int): Boolean {
                return adapter.getModelAtPosition(position)::class == StickyHeaderModel_::class
            }
        }
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = StickyHeaderLinearLayoutManager(requireContext())
            recyclerView.adapter = stickyHeaderController.adapter
        }
        viewModel.getGitHubRepositoryData(INITIAL)
        viewModel.uiLive.observe(viewLifecycleOwner, Observer {
            updateViews(it.first, it.second)
        })
    }

    private fun updateViews(uiState: UiState, data: UiData?) {
        when(uiState) {
            BLANK, PARTIAL -> Unit
            ERROR -> {
                binding.progressBar.visibility = View.GONE
            }
            LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            IDEAL -> {
                val uiData = data ?: throw IllegalArgumentException("Illegal results are being returned. Please review the communication.")
                binding.progressBar.visibility = View.GONE
                stickyHeaderController.setData(uiData.list)
            }
        }
    }

    companion object {
        private const val STICKY_HEADER_ID = "sticky_header_id"
        private const val STICKY_CONTENTS_ID = "sticky_contents_id"
        private const val INITIAL = 0
    }
}
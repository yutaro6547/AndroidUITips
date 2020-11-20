package www.rozkey59.tokyo.main.ui.presentation.list

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelCollector
import dagger.hilt.android.AndroidEntryPoint
import www.rozkey59.tokyo.androiduitips.core.ui.other.DisableSnapHelperCarouselBuilder
import www.rozkey59.tokyo.androiduitips.core.ui.other.DisableSnapHelperCarouselModel_
import www.rozkey59.tokyo.androiduitips.core.ui.other.SpeedConstraintGridLayoutManager
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.databinding.FragmentListBinding
import www.rozkey59.tokyo.androiduitips.main.ui.model.ContributorsModel_
import www.rozkey59.tokyo.androiduitips.main.ui.model.grid
import www.rozkey59.tokyo.androiduitips.main.ui.model.listRow
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    @Inject
    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = SpeedConstraintGridLayoutManager(requireContext(),
                SPAN_COUNT
            )
            fab.setOnClickListener {
                viewModel.getGitHubRepositoryData(INITIAL, true)
            }
        }
        viewModel.getGitHubRepositoryData(INITIAL)
        viewModel.uiLive.observe(viewLifecycleOwner, Observer {
            updateViews(it.first, it.second)
        })
    }

    private fun updateViews(uiState: UiState, data: UiData?) {
        when(uiState) {
            UiState.BLANK, UiState.PARTIAL -> Unit
            UiState.ERROR -> {
                binding.progressBar.visibility = View.GONE
            }
            UiState.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            UiState.IDEAL -> {
                val uiData = data ?: throw IllegalArgumentException("Illegal results are being returned. Please review the communication.")
                val list = uiData.list
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.withModels {
                    val carouselList = list.map {
                        ContributorsModel_()
                            .apply {
                                id(CONTRIBUTORS_ID, "${it.id}")
                                listData(it)
                                onRootClickListener { _ ->
                                    Toast.makeText(requireContext(), "Press ${it.name}!", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                    customCarousel {
                        val margin12 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12F, resources.displayMetrics).toInt()
                        val margin16 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16F, resources.displayMetrics).toInt()
                        id(CAROUSEL_ID)
                        models(carouselList)
                        spanSizeOverride { _, _, _ -> COLUMN1 }
                        numViewsToShowOnScreen(4.5f)
                        padding(Carousel.Padding(0, margin16, 0, margin12, 0))
                    }

                    val gridList = list.take(6)
                    gridList.forEach {
                        grid {
                            id(GRID_ID, "${it.id}")
                            listData(it)
                            spanSizeOverride { _, _, _ -> COLUMN2 }
                            onRootClickListener { _ ->
                                Toast.makeText(requireContext(), "Press ${it.name}!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    list.forEach {
                        listRow {
                            id(LIST_ROW_ID, "${it.id}")
                            listData(it)
                            isChanged(data.isChanged)
                            spanSizeOverride { _, _, _ -> COLUMN1 }
                            onRootClicked { _ ->
                                Toast.makeText(requireContext(), "Press ${it.name}!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun ModelCollector.customCarousel(builder: DisableSnapHelperCarouselBuilder.() -> Unit) : DisableSnapHelperCarouselModel_ {
        val carouselBuilder = DisableSnapHelperCarouselBuilder().apply { builder() }
        add(carouselBuilder.disableSnapHelperCarousel)
        return carouselBuilder.disableSnapHelperCarousel
    }

    companion object {
        private const val LIST_ROW_ID = "label_card_id"
        private const val CONTRIBUTORS_ID = "contributors_id"
        private const val CAROUSEL_ID = "carousel_id"
        private const val GRID_ID = "grid_id"
        private const val INITIAL = 0
        private const val SPAN_COUNT = 2
        private const val COLUMN1 =
            SPAN_COUNT
        private const val COLUMN2 = SPAN_COUNT / 2
    }
}
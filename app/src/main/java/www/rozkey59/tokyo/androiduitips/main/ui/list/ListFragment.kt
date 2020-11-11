package www.rozkey59.tokyo.androiduitips.main.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import www.rozkey59.tokyo.androiduitips.databinding.FragmentListBinding
import www.rozkey59.tokyo.androiduitips.main.ui.model.cardElement

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel

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
        viewModel = ListViewModel()
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            fab.setOnClickListener {
                recyclerView.requestModelBuild()
            }
        }
        viewModel.getGitHubRepositoryData()
        viewModel.uiLive.observe(viewLifecycleOwner, Observer { list ->
            binding.recyclerView.withModels {
                list.forEach {
                    cardElement {
                        id(LABEL_CARD_ID, "${it.id}")
                        numberText(it.name)
                        cardClickListener { _ ->
                            Toast.makeText(requireContext(), "Press ${it.name}!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    companion object {
        private const val LABEL_CARD_ID = "label_card_id"
    }
}
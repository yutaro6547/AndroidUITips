package www.rozkey59.tokyo.androiduitips.main.ui.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import www.rozkey59.tokyo.androiduitips.databinding.FragmentGridBinding
import www.rozkey59.tokyo.androiduitips.main.ui.list.ListFragment
import www.rozkey59.tokyo.androiduitips.main.ui.model.cardElement

class GridFragment: Fragment() {

    private lateinit var binding: FragmentGridBinding
    private val dataList = mutableListOf<Pair<Int, String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            recyclerView.withModels {
                dataList.forEach {
                    val (index, data) = it
                    cardElement {
                        id(LABEL_CARD_ID, "$index")
                        numberText(data)
                        cardClickListener { _ ->
                            Toast.makeText(requireContext(), "Press $data!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                swipeRefresh.isRefreshing = false
            }
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                val newList = dataList.map { pair ->
                    if (pair.first == 0) {
                        pair.first to "Data!"
                    } else {
                        pair
                    }
                }
                dataList.clear()
                dataList.addAll(newList)
                recyclerView.requestModelBuild()
            }
        }
    }

    private fun initializeData() {
        for(i in 0..21) {
            dataList.add(i to i.toString())
        }
    }

    companion object {
        private const val LABEL_CARD_ID = "label_card_id"
        private const val SPAN_COUNT = 2
    }
}
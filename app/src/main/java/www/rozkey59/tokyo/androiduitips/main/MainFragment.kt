package www.rozkey59.tokyo.androiduitips.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_label.*
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentMainBinding
import www.rozkey59.tokyo.androiduitips.label
import www.rozkey59.tokyo.androiduitips.main.ui.model.labelTitle

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val titleList = createDataList()
            recyclerView.withModels {
                titleList.forEach { element ->
                    labelTitle {
                        id("$LABEL_TITLE_ID/${element.title}")
                        titleText(element.title)
                        titleClickListener { _ ->
                            Toast.makeText(requireContext(), "Not implements.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun createDataList(): List<MainListData> {
        val dataList = mutableListOf<MainListData>()
        val titleList = resources.getStringArray(R.array.title_list)
        for (element in titleList) {
            dataList.add(MainListData(element))
        }
        return dataList
    }

    companion object {
        private const val LABEL_TITLE_ID = "labelTitle"
    }
}
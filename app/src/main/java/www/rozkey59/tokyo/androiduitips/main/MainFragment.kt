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
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentMainBinding

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
        with(binding) {
            val adapter = MainListRowAdapter(createDataList())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            adapter.setOnItemClickListener(object :
                MainListRowAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int, data: MainListData) {
                    when (position) {
                        0 -> {
                            findNavController(view).navigate(R.id.nav_graph_carousel)
                        }
                        1 -> {
                            findNavController(view).navigate(R.id.nav_graph_grid_2)
                        }
                        2 -> {
                            findNavController(view).navigate(R.id.nav_graph_grid_3)
                        }
                        3 -> {
                            findNavController(view).navigate(R.id.nav_graph_bottom_navigation)
                        }
                        4 -> {
                            findNavController(view).navigate(R.id.nav_graph_groupie)
                        }
                        5 -> {
                            findNavController(view).navigate(R.id.navigationSampleFragment)
                        }
                        6 -> {
                            findNavController(view).navigate(R.id.passwordFragment)
                        }
                        else -> {
                            Toast.makeText(requireContext(), data.title, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun createDataList(): List<MainListData> {
        val dataList = mutableListOf<MainListData>()
        val titleList = resources.getStringArray(R.array.title_list)
        for (i in 0 until titleList.size) {
            dataList.add(MainListData(titleList[i]))
        }
        return dataList
    }
}
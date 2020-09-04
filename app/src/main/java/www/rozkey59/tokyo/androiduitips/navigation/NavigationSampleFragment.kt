package www.rozkey59.tokyo.androiduitips.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentNavigationSampleBinding
import www.rozkey59.tokyo.androiduitips.main.MainListData
import www.rozkey59.tokyo.androiduitips.main.MainListRowAdapter

class NavigationSampleFragment: Fragment() {

    private lateinit var binding: FragmentNavigationSampleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_navigation_sample, container, false)
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
                            findNavController().navigate(R.id.sampleScreen1Fragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.sampleScreen2Fragment)
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
        val titleList = resources.getStringArray(R.array.navigation_list)
        for (i in 0 until titleList.size) {
            dataList.add(MainListData(titleList[i]))
        }
        return dataList
    }
}
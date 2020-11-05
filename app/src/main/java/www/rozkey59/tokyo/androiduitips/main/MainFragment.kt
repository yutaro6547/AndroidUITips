package www.rozkey59.tokyo.androiduitips.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentMainBinding
import www.rozkey59.tokyo.androiduitips.main.ui.bottom.BottomNavigationActivity
import www.rozkey59.tokyo.androiduitips.main.ui.model.labelTitle

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
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
                            switchFragment(element.id)
                        }
                    }
                }
            }
        }
    }

    private fun switchFragment(id: Int) {
        val navController = findNavController()
        if (navController.currentDestination?.id != R.id.mainFragment) return
        when(id) {
            0 -> navController.navigate(MainFragmentDirections.actionMainFragmentToListFragment())
            1 -> navController.navigate(MainFragmentDirections.actionMainFragmentToGridFragment())
            2 -> navController.navigate(MainFragmentDirections.actionMainFragmentToCarouselFragment())
            3 -> startActivity(BottomNavigationActivity.createIntent(requireContext()))
            else -> Toast.makeText(requireContext(), "Not implements.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createDataList(): List<MainListData> {
        return resources.getStringArray(R.array.title_list).mapIndexed { index, title -> MainListData(index, title) }
    }

    companion object {
        private const val LABEL_TITLE_ID = "labelTitle"
    }
}
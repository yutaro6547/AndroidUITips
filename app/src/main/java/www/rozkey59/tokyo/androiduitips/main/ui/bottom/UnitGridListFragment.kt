package www.rozkey59.tokyo.androiduitips.main.ui.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import www.rozkey59.tokyo.androiduitips.databinding.FragmentListBinding
import www.rozkey59.tokyo.androiduitips.databinding.FragmentUnitGridListBinding
import www.rozkey59.tokyo.androiduitips.databinding.FragmentUnitListBinding
import www.rozkey59.tokyo.androiduitips.main.ui.model.cardElement

class UnitGridListFragment: Fragment() {

    private lateinit var binding: FragmentUnitGridListBinding
    private val dataList = mutableListOf<Pair<Int, String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnitGridListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
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
            }
        }
    }

    private fun initializeData() {
        for(i in 0..10) {
            dataList.add(i to i.toString())
        }
    }

    companion object {
        private const val LABEL_CARD_ID = "label_card_id"
    }
}
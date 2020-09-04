package www.rozkey59.tokyo.androiduitips.main.ui.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.carousel
import www.rozkey59.tokyo.androiduitips.databinding.FragmentCarouselBinding
import www.rozkey59.tokyo.androiduitips.main.ui.grid.GridFragment
import www.rozkey59.tokyo.androiduitips.main.ui.model.DoubleRoundIconModel_
import www.rozkey59.tokyo.androiduitips.main.ui.model.cardElement
import www.rozkey59.tokyo.androiduitips.main.ui.model.doubleRoundIcon
import www.rozkey59.tokyo.androiduitips.main.ui.model.labelTitle

class CarouselFragment: Fragment() {

    private lateinit var binding: FragmentCarouselBinding
    private val dataList = mutableListOf<Pair<Int, String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()
        binding.apply {
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            recyclerView.withModels {
                val carouselElementsList = dataList.map {
                    DoubleRoundIconModel_()
                        .apply {
                            id(LABEL_DOUBLE_ROUND_ICON_ID, it.second)
                            iconClickListener { _ ->
                                Toast.makeText(requireContext(), "Press ${it.second}!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                labelTitle {
                    id(LABEL_TITLE_ID)
                    titleText("Carousel")
                    spanSizeOverride { _, _, _ -> COLUMN1 }
                    shouldHideRightArrowIcon(true)
                }
                carousel {
                    id(LABEL_CAROUSEL_ID)
                    models(carouselElementsList)
                    spanSizeOverride { _, _, _ -> COLUMN1 }
                }
                labelTitle {
                    id(LABEL_TITLE_ID)
                    titleText("GridList")
                    spanSizeOverride { _, _, _ -> COLUMN1 }
                    shouldHideRightArrowIcon(true)
                }
                dataList.forEach {
                    val (index, data) = it
                    cardElement {
                        id(LABEL_CARD_ID, "$index")
                        numberText(data)
                        spanSizeOverride { _, _, _ -> COLUMN3 }
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
        for(i in 0..32) {
            dataList.add(i to i.toString())
        }
    }

    companion object {
        private const val LABEL_TITLE_ID = "label_title_id"
        private const val LABEL_CARD_ID = "label_card_id"
        private const val LABEL_DOUBLE_ROUND_ICON_ID = "label_double_round_icon_id"
        private const val LABEL_CAROUSEL_ID = "label_carousel_id"
        private const val SPAN_COUNT = 3
        private const val COLUMN1 = SPAN_COUNT
        private const val COLUMN3 = SPAN_COUNT / 3
    }
}
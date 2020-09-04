package www.rozkey59.tokyo.androiduitips.groupie

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.carousel.CarouselItemDecoration
import www.rozkey59.tokyo.androiduitips.databinding.ItemCarouselBinding

class CarouselItem(private val cards: List<CardItem>) : BindableItem<ItemCarouselBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_carousel
    }

    override fun bind(binding: ItemCarouselBinding, position: Int) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(cards)
        }
        binding.recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(CarouselItemDecoration(16))
        }
    }
}
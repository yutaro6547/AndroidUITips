package www.rozkey59.tokyo.androiduitips.groupie

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemCarouselBinding
import www.rozkey59.tokyo.androiduitips.databinding.ItemGridGroupieBinding
import www.rozkey59.tokyo.androiduitips.grid.three.GridThreeItemDecoration

class GridItem(private val cards: List<CardItem>) : BindableItem<ItemGridGroupieBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_grid_groupie
    }

    override fun bind(binding: ItemGridGroupieBinding, position: Int) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(cards)
        }
        binding.recyclerView.apply {
            adapter = groupAdapter
            layoutManager = GridLayoutManager(binding.root.context, 3)
            addItemDecoration(GridThreeItemDecoration(16))
        }
    }
}
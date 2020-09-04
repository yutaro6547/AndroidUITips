package www.rozkey59.tokyo.androiduitips.groupie

import com.xwray.groupie.databinding.BindableItem
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemHeaderBinding

class HeaderItem(val headerTitle: String): BindableItem<ItemHeaderBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_header
    }

    override fun bind(binding: ItemHeaderBinding, position: Int) {
        binding.headerTitle.text = headerTitle
    }

}
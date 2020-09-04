package www.rozkey59.tokyo.androiduitips.groupie

import com.xwray.groupie.databinding.BindableItem
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemCardBinding

class CardItem : BindableItem<ItemCardBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_card
    }

    override fun bind(binding: ItemCardBinding, position: Int) {
        // TODO: ここに書く
    }

}
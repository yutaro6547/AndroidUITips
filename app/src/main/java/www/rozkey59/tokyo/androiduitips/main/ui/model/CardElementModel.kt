package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemCardBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.DataBindingModel

@EpoxyModelClass(layout = R.layout.item_card)
abstract class CardElementModel: DataBindingModel<ItemCardBinding>() {

    @EpoxyAttribute
    var numberText: String = ""

    @EpoxyAttribute(DoNotHash)
    var cardClickListener: View.OnClickListener? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_card
    }

    override fun bind(binding: ItemCardBinding) {
        binding.apply {
            number.text = numberText
            card.setOnClickListener(cardClickListener)
        }
    }

    override fun bindUpdating(binding: ItemCardBinding, previouslyBoundModel: EpoxyModel<*>?) {
        binding.apply {
            number.text = "Updating$numberText"
            card.setOnClickListener(cardClickListener)
        }
    }

    override fun unbind(binding: ItemCardBinding) {
        binding.card.setOnClickListener(null)
    }
}
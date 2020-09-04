package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemCardBinding
import www.rozkey59.tokyo.androiduitips.databinding.ItemDoubleRoundBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.DataBindingModel

@EpoxyModelClass(layout = R.layout.item_double_round)
abstract class DoubleRoundIconModel: DataBindingModel<ItemDoubleRoundBinding>() {

    @EpoxyAttribute(DoNotHash)
    var iconClickListener: View.OnClickListener? = null

    override fun bind(binding: ItemDoubleRoundBinding) {
        binding.icon.setOnClickListener(iconClickListener)
    }

    override fun bindUpdating(binding: ItemDoubleRoundBinding, previouslyBoundModel: EpoxyModel<*>?) = Unit

    override fun unbind(binding: ItemDoubleRoundBinding) {
        binding.icon.setOnClickListener(null)
    }
}
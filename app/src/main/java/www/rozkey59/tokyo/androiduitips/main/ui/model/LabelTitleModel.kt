package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ItemLabelBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.DataBindingModel

@EpoxyModelClass(layout = R.layout.item_label)
abstract class LabelTitleModel: DataBindingModel<ItemLabelBinding>() {

    @EpoxyAttribute
    var titleText: String = ""

    @JvmField
    @EpoxyAttribute
    var shouldHideRightArrowIcon: Boolean = false

    @EpoxyAttribute(DoNotHash)
    var titleClickListener: View.OnClickListener? = null

    override fun bind(binding: ItemLabelBinding) {
        binding.apply {
            if (shouldHideRightArrowIcon) {
                label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            label.text = titleText
            label.setOnClickListener(titleClickListener)
        }
    }

    override fun bindUpdating(binding: ItemLabelBinding, previouslyBoundModel: EpoxyModel<*>?) = Unit

    override fun unbind(binding: ItemLabelBinding) {
        binding.label.setOnClickListener(null)
    }
}
package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyLabelBinding
import www.rozkey59.tokyo.androiduitips.core.ui.epoxy.DataBindingModel

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_label)
abstract class LabelTitleModel: DataBindingModel<EpoxyLabelBinding>() {

    @EpoxyAttribute
    var titleText: String = ""

    @JvmField
    @EpoxyAttribute
    var shouldHideRightArrowIcon: Boolean = false

    @EpoxyAttribute(DoNotHash)
    var titleClickListener: View.OnClickListener? = null

    override fun bind(binding: EpoxyLabelBinding, context: Context) {
        binding.apply {
            if (shouldHideRightArrowIcon) {
                label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            label.text = titleText
            label.setOnClickListener(titleClickListener)
        }
    }

    override fun bind(binding: EpoxyLabelBinding, context: Context, previouslyBoundModel: EpoxyModel<*>?) = Unit

    override fun unbind(binding: EpoxyLabelBinding) {
        binding.label.setOnClickListener(null)
    }
}
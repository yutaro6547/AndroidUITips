package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.core.ui.epoxy.DataBindingModel
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyStickyHeaderBinding

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_sticky_header)
abstract class StickyHeaderModel: DataBindingModel<EpoxyStickyHeaderBinding>() {

    @EpoxyAttribute
    var titleText: String = ""

    @EpoxyAttribute(DoNotHash)
    var onTitleClicked: View.OnClickListener? = null

    override fun bind(binding: EpoxyStickyHeaderBinding, context: Context) {
        binding.apply {
            title.text = titleText
            title.setOnClickListener(onTitleClicked)
        }
    }

    override fun bind(binding: EpoxyStickyHeaderBinding, context: Context, previouslyBoundModel: EpoxyModel<*>?) = Unit

    override fun unbind(binding: EpoxyStickyHeaderBinding) {
        binding.title.setOnClickListener(null)
    }
}
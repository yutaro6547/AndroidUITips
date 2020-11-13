package www.rozkey59.tokyo.androiduitips.main.ui.model

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.core.ui.epoxy.DataBindingModel
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyGridBinding
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyStickyContentsBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_sticky_contents)
abstract class StickyContentsModel: DataBindingModel<EpoxyStickyContentsBinding>() {

    @EpoxyAttribute
    lateinit var uiData: UiData

    @EpoxyAttribute(DoNotHash)
    var onRootClickListener: View.OnClickListener? = null

    override fun bind(binding: EpoxyStickyContentsBinding, context: Context) {
        binding.apply {
            description.text = uiData.description ?: "No Description."
            Glide.with(context)
                .load(uiData.userUrl)
                .centerCrop()
                .into(userIcon)
            userIcon.setColorFilter(R.color.black_transparent, PorterDuff.Mode.SRC_ATOP)
            root.setOnClickListener(onRootClickListener)
        }
    }

    override fun bind(
        binding: EpoxyStickyContentsBinding,
        context: Context,
        previouslyBoundModel: EpoxyModel<*>?
    ) {
        bind(binding, context)
    }

    override fun unbind(binding: EpoxyStickyContentsBinding) {
        binding.root.setOnClickListener(null)
    }
}
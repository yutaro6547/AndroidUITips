package www.rozkey59.tokyo.main.ui.presentation.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.core.ui.epoxy.DataBindingModel
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyContributorsBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_contributors)
abstract class ContributorsModel: DataBindingModel<EpoxyContributorsBinding>() {

    @EpoxyAttribute
    lateinit var listData: ListData

    @EpoxyAttribute(DoNotHash)
    var onRootClickListener: View.OnClickListener? = null

    override fun bind(binding: EpoxyContributorsBinding, context: Context) {
        binding.apply {
            name.text = listData.name
            Glide.with(context)
                .load(listData.userUrl)
                .centerCrop()
                .into(icon)
            root.setOnClickListener(onRootClickListener)
        }
    }

    override fun bind(
        binding: EpoxyContributorsBinding,
        context: Context,
        previouslyBoundModel: EpoxyModel<*>?
    ) {
        bind(binding, context)
    }

    override fun unbind(binding: EpoxyContributorsBinding) {
        binding.root.setOnClickListener(null)
    }
}
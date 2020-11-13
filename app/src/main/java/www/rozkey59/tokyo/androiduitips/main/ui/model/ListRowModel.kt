package www.rozkey59.tokyo.androiduitips.main.ui.model

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
import www.rozkey59.tokyo.androiduitips.databinding.EpoxyListRowBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_list_row)
abstract class ListRowModel: DataBindingModel<EpoxyListRowBinding>() {

    @EpoxyAttribute
    lateinit var listData: ListData

    @JvmField
    @EpoxyAttribute
    var isChanged: Boolean = false

    @EpoxyAttribute(DoNotHash)
    var onRootClicked: View.OnClickListener? = null

    override fun bind(binding: EpoxyListRowBinding, context: Context) {
        binding.apply {
            label.visibility = View.GONE
            name.text = listData.name
            description.text = listData.description ?: "No Description."
            Glide.with(context)
                .load(listData.userUrl)
                .centerCrop()
                .into(userIcon)
            root.setOnClickListener(onRootClicked)
        }
    }

    override fun bind(
        binding: EpoxyListRowBinding,
        context: Context,
        previouslyBoundModel: EpoxyModel<*>?
    ) {
        if (previouslyBoundModel !is ListRowModel) return
        if (previouslyBoundModel.isChanged == isChanged) {
            bind(binding, context)
            return
        }

        binding.apply {
            label.visibility = View.VISIBLE
        }
    }

    override fun unbind(binding: EpoxyListRowBinding) {
        binding.root.setOnClickListener(null)
        binding.userIcon.setImageDrawable(null)
    }
}
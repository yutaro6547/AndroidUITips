package www.rozkey59.tokyo.androiduitips.main.ui.other

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyModel

abstract class DataBindingModel<in T : ViewDataBinding> : DataBindingEpoxyModel() {

    abstract fun bind(binding: T)

    abstract fun bindUpdating(binding: T, previouslyBoundModel: EpoxyModel<*>?)

    abstract fun unbind(binding: T)

    @Suppress("UNCHECKED_CAST")
    override fun setDataBindingVariables(dataBinding: ViewDataBinding?) {
        val binding = dataBinding as? T ?: return
        bind(binding)
    }

    @Suppress("UNCHECKED_CAST")
    override fun setDataBindingVariables(
        dataBinding: ViewDataBinding?,
        previouslyBoundModel: EpoxyModel<*>?
    ) {
        val binding = dataBinding as? T ?: return
        bindUpdating(binding, previouslyBoundModel)
    }

    @Suppress("UNCHECKED_CAST")
    override fun unbind(holder: DataBindingHolder) {
        val binding = holder.dataBinding as? T ?: return
        unbind(binding)
    }
}
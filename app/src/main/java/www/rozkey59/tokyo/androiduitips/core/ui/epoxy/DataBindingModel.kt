package www.rozkey59.tokyo.androiduitips.core.ui.epoxy

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelWithHolder

abstract class DataBindingModel<in T : ViewDataBinding> : EpoxyModelWithHolder<DataBindingEpoxyHolder>() {

    abstract fun bind(binding: T, context: Context)

    abstract fun bind(binding: T, context: Context, previouslyBoundModel: EpoxyModel<*>?)

    abstract fun unbind(binding: T)

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: DataBindingEpoxyHolder) {
        val binding = holder.binding as? T ?: return
        val context = binding.root.context
        bind(binding, context)
    }

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: DataBindingEpoxyHolder, previouslyBoundModel: EpoxyModel<*>) {
        val binding = holder.binding as? T ?: return
        val context = binding.root.context
        bind(binding, context, previouslyBoundModel)
    }

    @Suppress("UNCHECKED_CAST")
    override fun unbind(holder: DataBindingEpoxyHolder) {
        val binding = holder.binding as? T ?: return
        unbind(binding)
    }
}
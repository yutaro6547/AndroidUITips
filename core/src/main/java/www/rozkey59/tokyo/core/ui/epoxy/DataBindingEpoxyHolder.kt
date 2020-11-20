package www.rozkey59.tokyo.core.ui.epoxy

import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.EpoxyHolder

class DataBindingEpoxyHolder : EpoxyHolder() {

    @CallSuper
    override fun bindView(itemView: View) {
        binding = DataBindingUtil.bind(itemView) ?: throw DataBindingLayoutException()
    }

    lateinit var binding: ViewDataBinding
}
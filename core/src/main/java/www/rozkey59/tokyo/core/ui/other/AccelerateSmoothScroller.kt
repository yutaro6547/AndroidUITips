package www.rozkey59.tokyo.core.ui.other

import android.content.Context
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.LinearSmoothScroller

class AccelerateSmoothScroller(context: Context) : LinearSmoothScroller(context) {
    private var isScrolled: Boolean = false

    override fun updateActionForInterimTarget(action: Action) {
        if (isScrolled) {
            action.jumpTo(targetPosition)
        } else {
            super.updateActionForInterimTarget(action)
            action.interpolator = AccelerateInterpolator(1.5F)
            isScrolled = true
        }
    }
}
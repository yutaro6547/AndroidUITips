package www.rozkey59.tokyo.core.ui.other

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpeedConstraintGridLayoutManager(private val context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {
    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val constraintSmoothScroller =
            www.rozkey59.tokyo.core.ui.other.AccelerateSmoothScroller(context)
        constraintSmoothScroller.targetPosition = position
        startSmoothScroll(constraintSmoothScroller)
    }
}
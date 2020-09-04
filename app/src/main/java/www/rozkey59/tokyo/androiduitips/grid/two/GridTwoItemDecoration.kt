package www.rozkey59.tokyo.androiduitips.grid.two

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridTwoItemDecoration(val margin: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            top = margin / 2
            left = margin / 2
            right = margin / 2
            bottom = margin / 2
        }
    }
}
package www.rozkey59.tokyo.androiduitips.grid.three

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridThreeItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        when (parent.getChildAdapterPosition(view) % 3) {
            0 -> {
                with(outRect) {
                    top = margin / 2
                    left = margin
                    right = margin / 2
                    bottom = margin / 2
                }
            }
            1 -> {
                with(outRect) {
                    top = margin / 2
                    left = margin / 2
                    right = margin / 2
                    bottom = margin / 2
                }
            }
            2 -> {
                with(outRect) {
                    top = margin / 2
                    left = margin / 2
                    right = margin
                    bottom = margin / 2
                }
            }
        }
    }
}
package www.rozkey59.tokyo.androiduitips.carousel

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CarouselItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            top = margin
            left = margin / 2
            right = margin / 2
            bottom = margin
        }
    }
}
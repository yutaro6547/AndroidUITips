package www.rozkey59.tokyo.androiduitips.carousel

import android.graphics.drawable.Drawable

data class CarouselListData(
    val image: Drawable,
    val imageResId: Int = 0,
    val title: String,
    val color: Int
)
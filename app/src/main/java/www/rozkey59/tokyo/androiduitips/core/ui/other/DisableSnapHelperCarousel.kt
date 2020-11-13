package www.rozkey59.tokyo.androiduitips.core.ui.other

import android.content.Context
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DisableSnapHelperCarousel(context: Context) : Carousel(context) {
    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null
    }
}
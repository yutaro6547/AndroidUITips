package www.rozkey59.tokyo.androiduitips.core.ui.other

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector

class DisableSnapHelperCarouselBuilder(
    val disableSnapHelperCarousel: DisableSnapHelperCarouselModel_ = DisableSnapHelperCarouselModel_()
) : ModelCollector, DisableSnapHelperCarouselModelBuilder by disableSnapHelperCarousel {
    private val models = mutableListOf<EpoxyModel<*>>()
    override fun add(model: EpoxyModel<*>) {
        models.add(model)
        disableSnapHelperCarousel.models(models)
    }
}
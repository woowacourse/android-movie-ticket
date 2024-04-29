package woowacourse.movie.domain.repository

import woowacourse.movie.R
import woowacourse.movie.domain.model.DrawableImage
import woowacourse.movie.domain.model.ScreenAd

class DummyAdvertisement : AdRepository {
    override fun load(): ScreenAd.Advertisement = ScreenAd.Advertisement(0, DrawableImage(R.drawable.advertisement))
}

interface AdRepository {
    fun load(): ScreenAd.Advertisement
}

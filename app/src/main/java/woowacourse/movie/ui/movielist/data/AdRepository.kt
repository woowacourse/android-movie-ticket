package woowacourse.movie.ui.movielist.data

import woowacourse.movie.R
import woowacourse.movie.model.MovieItem

object AdRepository {
    fun allAds(): List<MovieItem.AdvertisementUI> = ads
    private val ads: List<MovieItem.AdvertisementUI> = listOf(
        MovieItem.AdvertisementUI(
            R.drawable.img_default_advertisement,
            "https://woowacourse.github.io/"
        ),
        MovieItem.AdvertisementUI(
            R.drawable.img_default_advertisement2,
            "https://www.woowahan.com/"
        )
    )
}

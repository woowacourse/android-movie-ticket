package woowacourse.movie.view.model

import woowacourse.movie.R
import woowacourse.movie.model.movie.Advertisement.Companion.JOIN_COUPON_BANNER_ID
import woowacourse.movie.model.movie.Advertisement.Companion.NIGHT_SALE_BANNER_ID
import woowacourse.movie.model.movie.Advertisement.Companion.WOOWA_TECH_BANNER_ID
import woowacourse.movie.model.movie.screening.Screening.Companion.HARRY_POTTER_1_MOVIE_ID

object ResourceMapper {
    fun movieIdToPosterImageResource(movieId: String): ImageResource =
        when (movieId) {
            HARRY_POTTER_1_MOVIE_ID -> ImageResource.Resource(R.drawable.poster_harry_potter_and_the_philosophers_stone)
            else -> ImageResource.Url("dummyImageUrl") // 기본 이미지
        }

    fun adIdToBannerImageResource(adId: String): ImageResource =
        when (adId) {
            WOOWA_TECH_BANNER_ID -> ImageResource.Resource(R.drawable.ad_woowa_tech)
            JOIN_COUPON_BANNER_ID -> ImageResource.Resource(R.drawable.ad_join_coupon)
            NIGHT_SALE_BANNER_ID -> ImageResource.Resource(R.drawable.ad_night_sale)
            else -> ImageResource.Url("dummyImageUrl") // 기본 이미지
        }
}

package woowacourse.movie.view.reservation

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Advertisement
import woowacourse.movie.view.util.ErrorMessage

object AdvertisementImage {
    @DrawableRes
    fun Advertisement.advertisementResourceId(): Int = advertisements[id] ?: error(ErrorMessage("advertisementResourceId").noSuch())

    private val advertisements: Map<Int, Int> =
        mapOf(
            0 to R.drawable.advertiesement_woowahan_techcourse,
        )
}

package woowacourse.movie.model

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class AdvertisementUI(
    @DrawableRes val image: Int?,
    val url: String
) : ItemUI {
    companion object {
        fun provideDummy(): List<AdvertisementUI> = listOf(
            AdvertisementUI(
                R.drawable.img_default_advertisement,
                "https://woowacourse.github.io/"
            ),
            AdvertisementUI(
                R.drawable.img_default_advertisement2,
                "https://www.woowahan.com/"
            )
        )
    }
}

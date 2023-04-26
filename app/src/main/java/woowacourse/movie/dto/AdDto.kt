package woowacourse.movie.dto

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.movielist.ViewType

data class AdDto(
    val viewType: ViewType = ViewType.AD_VIEW,
    @DrawableRes val adImage: Int,
    val url: String,
) {
    companion object {
        fun getAdData(): AdDto {
            return AdDto(
                adImage = R.drawable.ad,
                url = "https://woowacourse.github.io/",
            )
        }
    }
}

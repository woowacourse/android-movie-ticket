package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class Advertisement(
    @DrawableRes val adImageSrc: Int?,
    val url: String,
)

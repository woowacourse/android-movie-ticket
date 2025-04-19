package woowacourse.movie.ui.mapper

import woowacourse.movie.R

object PosterMapper {
    fun mapPosterKeyToDrawableRes(key: String): Int? =
        when (key) {
            "match" -> R.drawable.match
            "mickey" -> R.drawable.mickey
            else -> null
        }
}

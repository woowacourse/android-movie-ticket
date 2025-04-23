package woowacourse.movie.ui.util

import androidx.annotation.DrawableRes
import woowacourse.movie.R

object PosterMapper {
    private val posters = mapOf(
        "승부" to R.drawable.match,
        "미키 17" to R.drawable.mickey
    )

    @DrawableRes
    fun convertTitleToResId(name: String): Int = posters[name] ?: R.drawable.ic_launcher_foreground
}
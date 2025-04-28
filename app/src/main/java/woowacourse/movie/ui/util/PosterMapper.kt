package woowacourse.movie.ui.util

import androidx.annotation.DrawableRes
import woowacourse.movie.R

object PosterMapper {
    private val posters = mapOf(
        "승부" to R.drawable.match,
        "미키 17" to R.drawable.mickey,
        "야당" to R.drawable.yadang,
        "위플래쉬" to R.drawable.whiplash,
        "고독한 미식가 더 무비" to R.drawable.godok,
        "너의 췌장을 먹고 싶어" to R.drawable.pancreas,
        "로비" to R.drawable.lobby
    )

    @DrawableRes
    fun convertTitleToResId(name: String): Int = posters[name] ?: R.drawable.ic_launcher_foreground
}
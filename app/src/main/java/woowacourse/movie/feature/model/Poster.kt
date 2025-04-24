package woowacourse.movie.feature.model

import androidx.annotation.DrawableRes
import woowacourse.movie.R

private val posters: Map<String, Int> =
    mapOf(
        "해리 포터와 마법사의 돌" to R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
    )

@DrawableRes
fun getPosterImage(title: String): Int = posters[title] ?: 0

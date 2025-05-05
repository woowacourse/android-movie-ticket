package woowacourse.movie.resource

import androidx.annotation.DrawableRes
import woowacourse.movie.R

private val posters: Map<String, Int> =
    mapOf(
        "해리 포터와 마법사의 돌" to R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
        "해리 포터와 비밀의 방" to R.drawable.img_harry_potter_and_the_chamber_of_secrets,
        "해리 포터와 아즈카반의 죄수" to R.drawable.img_harry_potter_and_the_prisoner_of_azkaban,
        "해리 포터와 불의 잔" to R.drawable.img_harry_potter_and_the_goblet_of_fire,
    )

@DrawableRes
fun getPosterImage(title: String): Int = posters[title] ?: 0

package woowacourse.movie

import androidx.annotation.DrawableRes

data class MovieItem(
    val id: Long = -1L,
    val title: String = "해리 포터와 마법사의 돌",
    @DrawableRes val res: Int = R.drawable.img_movie_poster,
    val runningDate: String = "상영일: 2024.3.1",
    val runningTime: String = "러닝타임: 152분",
)

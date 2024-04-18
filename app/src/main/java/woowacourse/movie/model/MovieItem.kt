package woowacourse.movie.model

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class MovieItem(
    val id: Long = -1L,
    val title: String = "해리 포터와 마법사의 돌",
    @DrawableRes val imageRes: Int = R.drawable.img_movie_poster,
    val screenDate: String = "상영일: 2024.3.1",
    val runningTime: String = "러닝타임: 152분",
)

package woowacourse.movie.model

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class MovieReservationUiModel(
    val id: Long,
    val title: String,
    @DrawableRes val imageRes: Int = R.drawable.img_movie_poster,
    val screenDate: String = "상영일: 2024.3.1",
    val description: String = "",
    val runningTime: String = "러닝타임: 152분",
)

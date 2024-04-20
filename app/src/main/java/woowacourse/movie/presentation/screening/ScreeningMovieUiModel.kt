package woowacourse.movie.presentation.screening

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class ScreeningMovieUiModel(
    val id: Long,
    val title: String,
    @DrawableRes val imageRes: Int = R.drawable.img_movie_poster,
    val screenDate: String,
    val runningTime: String,
)

package woowacourse.movie.fixtures

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel

private const val DEFAULT_ID = 1L
private const val DEFAULT_TITLE = "해리포터"
private const val DEFAULT_SCREEN_DATE = "상영일: 2024.3.1"
private const val DEFAULT_RUNNING_TIME = "러닝타임: 152분"

fun screenMovieUiModel(
    id: Long = DEFAULT_ID,
    title: String = DEFAULT_TITLE,
    @DrawableRes imageRes: Int = R.drawable.img_movie_poster,
    screenDate: String = DEFAULT_SCREEN_DATE,
    runningTime: String = DEFAULT_RUNNING_TIME,
): ScreeningMovieUiModel = ScreeningMovieUiModel(
    id = id,
    title = title,
    imageRes = imageRes,
    screenDate = screenDate,
    runningTime = runningTime,
)
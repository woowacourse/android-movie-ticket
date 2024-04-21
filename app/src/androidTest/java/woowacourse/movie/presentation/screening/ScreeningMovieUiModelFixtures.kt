package woowacourse.movie.presentation.screening

import woowacourse.movie.data.FakeMovieRepository

private const val DEFAULT_ID = 1L
private const val DEFAULT_TITLE = "해리포터"
private const val DEFAULT_SCREEN_DATE = "상영일: 2024.3.1"
private const val DEFAULT_RUNNING_TIME = "러닝타임: 152분"

fun screenMovieUiModel(): ScreeningMovieUiModel = FakeMovieRepository.screenMovies().first().toScreenMovieUiModel()

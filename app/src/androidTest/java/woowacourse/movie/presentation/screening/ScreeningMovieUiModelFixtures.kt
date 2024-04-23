package woowacourse.movie.presentation.screening

import woowacourse.movie.data.FakeMovieRepository

fun screenMovieUiModel(): ScreeningMovieUiModel =
    FakeMovieRepository().screenMovies().first().toScreenMovieUiModel()

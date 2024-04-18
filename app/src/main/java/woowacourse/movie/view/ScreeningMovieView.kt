package woowacourse.movie.view

import woowacourse.movie.model.ScreenMovieUiModel

interface ScreeningMovieView {
    fun showMovies(movies: List<ScreenMovieUiModel>)

    fun navigateToMovieReservationView(reservationId: Long)
}
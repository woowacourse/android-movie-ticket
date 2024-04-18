package woowacourse.movie.view

import woowacourse.movie.model.Movie

interface ReservationFinishedContract {
    fun showMovieInformation(movie: Movie)

    fun showReservationHistory(
        ticketCount: Int,
        price: Int,
    )
}

package woowacourse.movie.feature.complete

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContents

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieContents: MovieContents,
) : MovieReservationCompleteContract.Presenter {
    override fun loadMovieData(
        movieContentId: Long,
        reservationCountValue: Int,
    ) {
        val movieContent = movieContents.find(movieContentId)
        val reservationCount =
            runCatching {
                ReservationCount(reservationCountValue)
            }.getOrElse {
                view.handleError(it)
                return
            }
        val ticket = Ticket(reservationCount)
        view.setUpReservationCompleteView(movieContent, ticket)
    }
}

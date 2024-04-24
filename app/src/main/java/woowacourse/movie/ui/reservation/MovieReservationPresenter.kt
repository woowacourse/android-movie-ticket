package woowacourse.movie.ui.reservation

import woowacourse.movie.model.data.MovieContents
import woowacourse.movie.model.movie.ReservationCount

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieContents: MovieContents,
) :
    MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun updateReservationCount(count: Int) {
        reservationCount = ReservationCount(count)
        view.updateReservationCountUi(count)
    }

    override fun loadMovieContent(movieContentId: Long) {
        try {
            val movieContent = movieContents.find(movieContentId)
            view.showMovieContentUi(movieContent)
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun decreaseCount() {
        reservationCount--
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun increaseCount() {
        reservationCount++
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun reserveMovie() {
        view.moveMovieReservationCompleteView(reservationCount.count)
    }

    override fun handleError(throwable: Throwable) {
        view.showError(throwable)
    }
}

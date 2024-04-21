package woowacourse.movie.ui.reservation

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.data.MovieContentsImpl

class MovieReservationPresenter(private val view: MovieReservationContract.View) :
    MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = MovieContentsImpl.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun decreaseCount() {
        reservationCount--
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun increaseCount() {
        reservationCount++
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun moveMovieReservationComplete() {
        view.moveMovieReservationCompleteView(reservationCount.count)
    }
}

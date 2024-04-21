package woowacourse.movie.ui.reservation

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.data.MovieContents

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieContents: MovieContents,
) :
    MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun setReservationCount(count: Int) {
        reservationCount = ReservationCount(count)
    }

    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = movieContents.find(movieContentId)
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

package woowacourse.movie.feature.reservation

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.data.MovieContents

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieContents: MovieContents,
) : MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = movieContents.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun decreaseReservationCount() {
        reservationCount--
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun increaseReservationCount() {
        reservationCount++
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun reserveMovie() {
        view.moveMovieReservationCompleteView(reservationCount.count)
    }

    override fun updateReservationCount(count: Int) {
        reservationCount = ReservationCount(count)
        view.updateReservationCountUi(reservationCount.count)
    }
}

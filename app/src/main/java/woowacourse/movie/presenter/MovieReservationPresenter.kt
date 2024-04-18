package woowacourse.movie.presenter

import woowacourse.movie.conrtract.MovieReservationContract
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

    override fun clickMinusButton() {
        reservationCount = --reservationCount
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun clickPlusButton() {
        reservationCount = ++reservationCount
        view.updateReservationCountUi(reservationCount.count)
    }

    override fun clickReservationButton() {
        view.moveMovieReservationCompleteView(reservationCount.count)
    }
}

package woowacourse.movie.presenter

import android.util.Log
import woowacourse.movie.conrtract.MovieReservationContract
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.ReservationCount

class MovieReservationPresenter(private val view: MovieReservationContract.View) :
    MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.setUpReservationCountUi(reservationCount.count)
    }

    override fun setUpMovieContent(movieContentId: Long) {
        val movieContent = MovieContentsImpl.find(movieContentId)
        view.setUpMovieContentUi(movieContent)
    }

    override fun clickMinusButton() {
        reservationCount = --reservationCount
        view.bindDecreasedReservationCount(reservationCount.count)
    }

    override fun clickPlusButton() {
        reservationCount = ++reservationCount
        view.bindIncreasedReservationCount(reservationCount.count)
    }

    override fun clickReservationButton() {
        view.moveMovieReservationCompleteView(reservationCount.count)
    }
}

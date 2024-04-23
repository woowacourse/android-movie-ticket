package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationContract {
    interface View : ErrorListener {
        fun setUpReservationView(movie: Movie)

        fun updateReservationCount(reservationCountValue: Int)

        fun moveReservationCompleteView(reservationCountValue: Int)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun setUpReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun reserveMovie()

        fun updateReservationCount(reservationCountValue: Int)
    }
}

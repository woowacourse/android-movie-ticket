package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationContract {
    interface View : ErrorListener {
        fun setUpReservationView(movieContent: MovieContent)

        fun updateReservationCount(reservationCountValue: Int)

        fun moveReservationCompleteView(reservationCountValue: Int)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieContentId: Long)

        fun setUpReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun reserveMovie()

        fun updateReservationCount(reservationCountValue: Int)
    }
}

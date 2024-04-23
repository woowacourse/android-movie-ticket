package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationContract {
    interface View : ErrorListener {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCountValue: Int)

        fun moveMovieReservationCompleteView(reservationCountValue: Int)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun reserveMovie()

        fun updateReservationCount(reservationCountValue: Int)
    }
}

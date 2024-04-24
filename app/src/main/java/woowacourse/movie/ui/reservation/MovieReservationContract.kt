package woowacourse.movie.ui.reservation

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.ui.HandleError

interface MovieReservationContract {
    interface View : HandleError {
        fun showMovieContent(movieContent: MovieContent)

        fun updateReservationCount(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateReservationCount(count: Int = ReservationCount.DEFAULT_VALUE)

        fun decreaseCount()

        fun increaseCount()

        fun reserveMovie()

        fun handleError(throwable: Throwable)
    }
}

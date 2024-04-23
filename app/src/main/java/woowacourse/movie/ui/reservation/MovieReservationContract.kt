package woowacourse.movie.ui.reservation

import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.ReservationCount

interface MovieReservationContract {
    interface View {
        fun showMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)

        fun showError(e: Exception)
    }

    interface Presenter {
        fun loadMovieContent(movieContentId: Long)

        fun updateReservationCount(count: Int = ReservationCount.DEFAULT_VALUE)

        fun decreaseCount()

        fun increaseCount()

        fun reserveMovie()
    }
}

package woowacourse.movie.ui.reservation

import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.ui.base.BaseView

interface MovieReservationContract {
    interface View : BaseView {
        fun updateMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter {
        fun updateMovieContent(movieContentId: Long)

        fun updateReservationCount(count: Int = ReservationCount.DEFAULT_VALUE)

        fun decreaseCount()

        fun increaseCount()

        fun reserveMovie()
    }
}

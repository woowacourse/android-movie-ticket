package woowacourse.movie.ui.reservation

import woowacourse.movie.model.MovieContent
import woowacourse.movie.ui.base.BaseView

interface MovieReservationContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpReservationCount()

        fun setReservationCount(count: Int)

        fun decreaseCount()

        fun increaseCount()

        fun moveMovieReservationComplete()
    }
}

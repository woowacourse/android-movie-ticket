package woowacourse.movie.ui.reservation

import woowacourse.movie.model.data.dto.MovieContent
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

        fun decreaseCount()

        fun increaseCount()

        fun moveMovieReservationComplete()
    }
}

package woowacourse.movie.feature.reservation

import woowacourse.movie.base.BasePresenter
import woowacourse.movie.base.BaseView
import woowacourse.movie.model.data.dto.MovieContent

interface MovieReservationContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun reserveMovie()

        fun updateReservationCount(count: Int)
    }
}

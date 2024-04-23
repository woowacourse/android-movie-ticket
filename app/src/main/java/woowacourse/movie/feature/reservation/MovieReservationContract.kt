package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter

interface MovieReservationContract {
    interface View {
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

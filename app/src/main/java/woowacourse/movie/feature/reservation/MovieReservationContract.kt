package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.base.BasePresenter
import woowacourse.movie.base.BaseView

interface MovieReservationContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun updateReservationCountUi(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpReservationCount()

        fun clickMinusButton()

        fun clickPlusButton()

        fun clickReservationButton()
    }
}

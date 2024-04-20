package woowacourse.movie.conrtract

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.presenter.BasePresenter
import woowacourse.movie.view.BaseView

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

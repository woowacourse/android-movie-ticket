package woowacourse.movie.conrtract

import woowacourse.movie.model.MovieContent
import woowacourse.movie.view.BaseView

interface MovieReservationContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpReservationCountUi(reservationCount: Int)

        fun bindDecreasedReservationCount(reservationCount: Int)

        fun bindIncreasedReservationCount(reservationCount: Int)

        fun moveMovieReservationCompleteView(reservationCount: Int)
    }

    interface Presenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpReservationCount()

        fun clickMinusButton()

        fun clickPlusButton()

        fun clickReservationButton()
    }
}

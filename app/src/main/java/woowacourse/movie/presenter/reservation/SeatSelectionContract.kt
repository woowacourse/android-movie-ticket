package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Grade
import woowacourse.movie.model.Seat

interface SeatSelectionContract {
    interface Presenter {
        fun loadSeatNumber()
    }

    interface View {
        fun showSeatNumber(
            index: Int,
            seat: Seat,
        )

        fun setUpSeatColorByGrade(grade: Grade): Int
    }
}

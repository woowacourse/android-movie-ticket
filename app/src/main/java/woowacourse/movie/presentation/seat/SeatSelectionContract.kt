package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.presentation.seat.model.SeatSelectType

interface SeatSelectionContract {
    interface View {
        fun showSeat(seats: List<List<MovieSeat>>)

        fun showSelectedSeat(
            rowIndex: Int,
            columnIndex: Int,
            selectType: SeatSelectType,
        )
    }

    interface Presenter {
        fun loadSeat()

        fun selectSeat(
            rowIndex: Int,
            columIndex: Int,
        )
    }
}

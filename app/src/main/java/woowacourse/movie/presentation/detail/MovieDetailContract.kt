package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.base.BaseContract
import java.time.LocalDate
import java.time.LocalTime

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun onUpdateView(detailUiModel: DetailUiModel)

        fun updateDateList(
            dates: List<LocalDate>?,
            selectedPosition: Int?,
        )

        fun updateTimeList(
            times: List<LocalTime>?,
            selectedPosition: Int?,
        )

        fun onSelectSeatClicked(
            movieId: Long?,
            movieScreenDateTimeId: Long?,
            count: Int,
        )
    }

    interface Presenter {
        fun loadMovie(id: Long)

        fun plusReservationCount()

        fun minusReservationCount()

        fun selectDate(
            localDate: LocalDate,
            position: Int,
        )

        fun selectSeat(
            localDate: LocalDate,
            localTime: LocalTime,
        )

        fun restoreDateTime(
            localDate: LocalDate,
            localTime: LocalTime,
        )
    }
}

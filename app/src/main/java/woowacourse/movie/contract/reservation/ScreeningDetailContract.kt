package woowacourse.movie.contract.reservation

import java.time.LocalDate
import java.time.LocalTime

interface ScreeningDetailContract {
    interface Presenter {
        val ticketCount: Int
        var timeItemPosition: Int

        fun fetchScreeningDetail()

        fun fetchAvailableTimes(date: LocalDate)

        fun plusTicketCount()

        fun minusTicketCount()

        fun fetchAvailableSeats()
    }

    interface View {
        fun setPoster(movieId: Int)

        fun setTitle(title: String)

        fun setPeriod(
            startYear: Int,
            startMonth: Int,
            startDay: Int,
            endYear: Int,
            endMonth: Int,
            endDay: Int,
        )

        fun setRunningTime(runningTime: Int)

        fun setDates(dates: List<LocalDate>)

        fun setTimes(
            times: List<LocalTime>,
            timeItemPosition: Int,
        )

        fun setTicketCount(count: Int)

        fun navigateToSeatSelectionScreen(
            title: String,
            ticketCount: Int,
        )
    }
}

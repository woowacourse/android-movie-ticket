package woowacourse.movie.contract.reservation

import java.time.LocalDate
import java.time.LocalTime

interface ReservationContract {
    interface Presenter {
        fun presentPoster()

        fun presentTitle()

        fun presentPeriod()

        fun presentRunningTime()

        fun presentDates()

        fun presentTimes(date: LocalDate)

        fun presentTicketCount()

        fun plusTicketCount()

        fun minusTicketCount()

        fun confirm()

        fun getTicketCount(): Int

        fun getItemPosition(): Int

        fun setTimeItemPosition(position: Int)
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

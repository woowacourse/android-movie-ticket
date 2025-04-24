package woowacourse.movie.contract

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

        fun setTimes(times: List<LocalTime>)
    }
}

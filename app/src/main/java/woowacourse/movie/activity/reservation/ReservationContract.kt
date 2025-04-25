package woowacourse.movie.activity.reservation

import woowacourse.movie.dto.MovieDto
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ReservationContract {
    interface Presenter {
        fun addMember()

        fun removeMember()

        fun initRunningTimes(
            now: LocalDateTime,
            reservationDay: LocalDate,
        )

        fun initRunningDates(
            today: LocalDate,
            movieDto: MovieDto,
        )

        fun changeRunningTimes(
            now: LocalDateTime,
            reservationDay: LocalDate,
        )
    }

    interface View {
        fun updateMemberCount(result: Result<Int>)

        fun initRunningTimes(runningTimes: List<LocalTime>)

        fun initRunningDates(runningDates: List<LocalDate>)

        fun changeRunningTimes(runningTimes: List<LocalTime>)
    }
}

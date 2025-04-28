package woowacourse.movie.activity.reservation

import woowacourse.movie.dto.MovieListDataDto.MovieDto
import java.time.LocalDate
import java.time.LocalTime

interface ReservationContract {
    interface Presenter {
        fun addMember()

        fun removeMember()

        fun initRunningTimes(reservationDay: LocalDate)

        fun initRunningDates(movieDto: MovieDto)

        fun changeRunningTimes(reservationDay: LocalDate)
    }

    interface View {
        fun updateMemberCount(result: Result<Int>)

        fun initRunningTimes(runningTimes: List<LocalTime>)

        fun initRunningDates(runningDates: List<LocalDate>)

        fun changeRunningTimes(runningTimes: List<LocalTime>)
    }
}

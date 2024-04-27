package woowacourse.movie.domain.screening

import java.time.LocalDate

data class Screening(
    val id: Long,
    val movie: Movie,
    val releaseDate: LocalDate,
    val endDate: LocalDate,
    private val scheduleSystem: ScreeningScheduleSystem = BasicScreeningScheduleSystem(),
) {
    val schedule: ScreeningSchedule = scheduleSystem.getSchedules(releaseDate, endDate)
}

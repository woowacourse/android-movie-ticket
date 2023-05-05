package woowacourse.movie.domain.screening

import java.time.*

data class ScreeningRange(val startDate: LocalDate, val endDate: LocalDate) {

    val screeningDateTimes: Map<LocalDate, List<LocalTime>> =
        createScreeningDateTimes(startDate, endDate)

    init {
        require(startDate <= endDate) { START_DATE_AFTER_END_DATE_ERROR }
    }

    private fun createScreeningDateTimes(
        startDate: LocalDate,
        endDate: LocalDate
    ): Map<LocalDate, List<LocalTime>> {
        val days = Period.between(startDate, endDate).days

        return (0..days)
            .map { startDate.plusDays(it.toLong()) }
            .associateWith { getScreeningTimesOf(it) }
    }

    private fun getScreeningTimesOf(date: LocalDate): List<LocalTime> {
        val startTime =
            if (date.isWeekend()) SCREENING_START_TIME_AT_WEEKEND else SCREENING_START_TIME_AT_WEEKDAY
        return startTime.getAllTimesToMidNight(SCREENING_TIME_INTERVAL)
    }

    private fun LocalDate.isWeekend(): Boolean =
        this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY

    private fun LocalTime.getAllTimesToMidNight(intervalHour: Long): List<LocalTime> {
        val times: MutableList<LocalTime> = mutableListOf()
        var time = this
        while (time >= this) {
            times.add(time)
            time = time.plusHours(intervalHour)
        }
        if (time == LocalTime.MIDNIGHT) times.add(time)
        return times
    }

    fun screenOn(dateTime: LocalDateTime): Boolean = dateTime in screeningDateTimes
            .flatMap { it.value.map { time -> LocalDateTime.of(it.key, time) } }


    companion object {
        private const val START_DATE_AFTER_END_DATE_ERROR = "시작 날짜는 마지막 날짜 이후일 수 없습니다."
        private val SCREENING_START_TIME_AT_WEEKEND = LocalTime.of(9, 0)
        private val SCREENING_START_TIME_AT_WEEKDAY = LocalTime.of(10, 0)
        private const val SCREENING_TIME_INTERVAL = 2L
    }
}

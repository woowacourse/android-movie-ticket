package domain.movie

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class ScreeningPeriod(
    val startDate: ScreeningDate,
    val endDate: ScreeningDate
) {

    init {
        require(startDate <= endDate) {
            PERIOD_ERROR
        }
    }

    constructor(startDate: LocalDate, endDate: LocalDate) : this(
        ScreeningDate(startDate),
        ScreeningDate(endDate)
    )

    fun getScreeningDates(): List<ScreeningDate> {
        var currentDate = startDate
        val totalScreeningDays = ChronoUnit.DAYS.between(startDate.value, endDate.value).toInt() + 1
        val screeningDates = mutableListOf<ScreeningDate>()

        repeat(totalScreeningDays) {
            screeningDates.add(currentDate)
            currentDate = ScreeningDate(currentDate.value.plusDays(1))
        }

        return screeningDates.toList()
    }

    companion object {

        private const val PERIOD_ERROR = "[ERROR] 상영 시작 일은 상영 종료일 보다 이전이어야 합니다"
    }
}

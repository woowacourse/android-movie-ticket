package woowacourse.movie.model

import java.time.LocalDate

class ScreeningDates(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    init {
        require(startDate <= endDate) { "유효하지 않는 날짜 범위 입니다." }
    }

    fun getDatesBetweenStartAndEnd(): List<LocalDate> {
        val datesInRange = mutableListOf<LocalDate>()
        var currentDate = startDate
        while (!currentDate.isAfter(endDate)) {
            datesInRange.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return datesInRange
    }
}

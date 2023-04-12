package domain

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class ScreeningPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate
) : Serializable {

    init {
        require(startDate <= endDate)
    }

    fun getScreeningDates(): List<LocalDate> {
        val screeningDates: MutableList<LocalDate> = mutableListOf()
        screeningDates.add(startDate)

        while (true) {
            if (screeningDates.last() >= endDate) break
            screeningDates.add(screeningDates.last().plusDays(1))
        }

        return screeningDates
    }

    fun getScreeningTimes(screeningDate: LocalDate?): List<LocalTime> {
        if (screeningDate == null) {
            return listOf()
        }
        return when (screeningDate.dayOfWeek) {
            in DayOfWeek.MONDAY..DayOfWeek.FRIDAY -> getScreeningTimes(10)
            in DayOfWeek.SATURDAY..DayOfWeek.SUNDAY -> getScreeningTimes(9)
            else -> listOf()
        }
    }

    private fun getScreeningTimes(startHour: Int, endHour: Int = 24): List<LocalTime> {
        val screeningTimes = mutableListOf<LocalTime>()
        var currentHour = startHour

        while (currentHour <= endHour) {
            screeningTimes.add(LocalTime.of(currentHour, 0))
            currentHour += 2
        }
        return screeningTimes.toList()
    }
}

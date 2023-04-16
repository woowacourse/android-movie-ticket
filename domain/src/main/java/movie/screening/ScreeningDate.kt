package movie.screening

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {

    fun getScreeningDate(): List<String> = (0..ChronoUnit.DAYS.between(startDate, endDate))
        .map { startDate.plusDays(it).toString() }
}

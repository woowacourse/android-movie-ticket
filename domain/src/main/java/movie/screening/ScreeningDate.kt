package movie.screening

import java.io.Serializable
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {

    fun getScreeningDate(): List<String> = (0..ChronoUnit.DAYS.between(startDate, endDate))
        .map { startDate.plusDays(it).toString() }
}

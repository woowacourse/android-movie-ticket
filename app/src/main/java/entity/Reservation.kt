package entity

import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Reservation(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    fun getReserveDateRange(): String {
        return "${startDate.format(dateTimeFormatter)} ~ ${endDate.format(dateTimeFormatter)}"
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}

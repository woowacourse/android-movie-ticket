package model

import java.io.Serializable
import java.time.LocalDate

class ReservationModel(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    companion object {
        val EMPTY = ReservationModel(LocalDate.MIN, LocalDate.MIN)
    }
}

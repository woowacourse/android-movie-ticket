package entity

import java.io.Serializable
import java.time.LocalDate

class Reservation(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable

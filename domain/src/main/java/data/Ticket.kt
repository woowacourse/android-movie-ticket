package data

import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val reserveTime: LocalDateTime,
    val price: Int,
    val peopleNumber: Int,
)

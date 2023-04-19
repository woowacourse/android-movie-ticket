package model

import java.io.Serializable
import java.time.LocalDateTime

class SeatSelectionModel(
    val title: String,
    val reserveTime: LocalDateTime,
    val peopleNumber: Int,
) : Serializable {
    companion object {
        val EMPTY = SeatSelectionModel("", LocalDateTime.MIN, 0)
    }
}

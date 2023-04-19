package model

import java.io.Serializable

class SeatModel(
    val row: Int,
    val column: Int,
    val seatClass: String,
    val isReserved: Boolean,
) : Serializable

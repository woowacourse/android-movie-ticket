package domain

import java.time.LocalDateTime

data class Seat(
    val row: SeatRow,
    val col: SeatCol
) {

    fun getGrade(): Grade = row.getGrade()

    fun applyPolicyPrice(dateTime: LocalDateTime): Int {
        return getGrade().ticketPrice.applyPolicy(dateTime).price
    }
}

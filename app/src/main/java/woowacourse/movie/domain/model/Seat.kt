package woowacourse.movie.domain.model

import java.io.Serializable

data class Seat(
    val row: Int,
    val col: Int,
    val ticketType: TicketType = TicketType.B_GRADE,
) : Serializable {
    fun price(): Int = ticketType.price

    companion object {
        fun fromSeatTag(seatTag: String): Seat {
            val row = seatTag[0] - ASCII_A
            val col = seatTag[1].digitToInt() - ONE_BASED
            val ticketType = ticketTypeByRow(row)
            return Seat(row, col, ticketType)
        }

        private fun ticketTypeByRow(row: Int): TicketType =
            when {
                row < 2 -> TicketType.B_GRADE
                row < 4 -> TicketType.S_GRADE
                row < 5 -> TicketType.A_GRADE
                else -> TicketType.B_GRADE
            }

        private const val ASCII_A = 'A'
        private const val ONE_BASED = 1
    }
}

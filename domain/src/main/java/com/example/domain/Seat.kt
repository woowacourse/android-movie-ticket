package com.example.domain

class Seat(seatIndex: Int) {
    private val row = seatIndex % SEAT_ROW_COUNT
    private val column = seatIndex / SEAT_ROW_COUNT

    private val columnSeatName = convertSeatNumberToAlphabet(column)

    fun getSeatName(): String {
        return columnSeatName + (row + SEAT_NUMBER_OFFSET)
    }

    private fun convertSeatNumberToAlphabet(seatNumber: Int): String {
        return (seatNumber + CHAR_CODE_A).toChar().toString()
    }

    fun getSeatGrade(): SeatGrade {
        return when (columnSeatName) {
            "A", "B" -> SeatGrade.B_CLASS
            "C", "D" -> SeatGrade.S_CLASS
            "E" -> SeatGrade.A_CLASS
            else -> SeatGrade.NONE
        }
    }

    companion object {
        private const val SEAT_ROW_COUNT = 4
        private const val SEAT_NUMBER_OFFSET = 1
        private const val CHAR_CODE_A = 65
    }
}

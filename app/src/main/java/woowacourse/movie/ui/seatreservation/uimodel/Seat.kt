package woowacourse.movie.ui.seatreservation.uimodel

import woowacourse.movie.ui.seatreservation.uimodel.SeatGrade.A_CLASS
import woowacourse.movie.ui.seatreservation.uimodel.SeatGrade.B_CLASS
import woowacourse.movie.ui.seatreservation.uimodel.SeatGrade.S_CLASS

class Seat {
    fun getSeatPrice(locationIndex: Int): SeatGrade =
        when (getSeatLocation(locationIndex).removeSeatLocation(locationIndex)) {
            GRADE_A, GRADE_B -> B_CLASS
            GRADE_C, GRADE_D -> S_CLASS
            GRADE_E -> A_CLASS
            else -> throw IllegalArgumentException()
        }

    fun getSeatLocation(locationIndex: Int): String = when (locationIndex / GRADE_UNIT) {
        0 -> GRADE_A.toSeatLocation(locationIndex)
        1 -> GRADE_B.toSeatLocation(locationIndex)
        2 -> GRADE_C.toSeatLocation(locationIndex)
        3 -> GRADE_D.toSeatLocation(locationIndex)
        4 -> GRADE_E.toSeatLocation(locationIndex)
        else -> throw IllegalArgumentException()
    }

    private fun String.removeSeatLocation(locationIndex: Int): String =
        this.removeSuffix(locationIndex.toSeatNumber().toString())

    private fun String.toSeatLocation(locationIndex: Int): String =
        this.plus(locationIndex.toSeatNumber())

    private fun Int.toSeatNumber(): Int = (this % GRADE_UNIT) + 1

    companion object {
        private const val GRADE_A = "A"
        private const val GRADE_B = "B"
        private const val GRADE_C = "C"
        private const val GRADE_D = "D"
        private const val GRADE_E = "E"
        private const val GRADE_UNIT = 4
    }
}

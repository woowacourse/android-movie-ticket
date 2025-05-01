package woowacourse.movie.model.seat

import java.io.Serializable

data class Seat(
    val row: SeatGridElement,
    val column: SeatGridElement,
) : Serializable {
    val price: Int get() = SeatGrade.calculateSeatGrade(row).price

    companion object {
        operator fun invoke(
            row: Int,
            column: Int,
        ): Seat = Seat(SeatGridElement(row), SeatGridElement(column))
    }
}

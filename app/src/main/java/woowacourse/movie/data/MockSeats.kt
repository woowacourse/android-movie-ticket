package woowacourse.movie.data

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatType

object MockSeats {
    private val rowList = listOf("A", "B", "C", "D", "E")
    private val columnList = listOf(1, 2, 3, 4)

    val sampleSeats =
        rowList.map { row ->
            columnList.map { column ->
                val seatType =
                    when (row) {
                        "A", "B" -> SeatType.B
                        "C", "D" -> SeatType.S
                        else -> SeatType.A
                    }
                MovieSeat(
                    seatRow = row,
                    seatColumn = column,
                    seatType = seatType,
                )
            }
        }

    val defaultSeat = MovieSeat(seatRow = "", seatColumn = -1, seatType = SeatType.S)
}

package woowacourse.movie.seats.model

import android.graphics.Color
import woowacourse.movie.seats.model.SeatsDataSource.seatTotalPrice
import java.io.Serializable

class Seat private constructor(val rowIndex: Int, val colIndex: Int) : Serializable {
    var selected = false
        private set

    var cellBackgroundColor = Color.WHITE
        private set

    val rank: SeatRank
        get() = SeatRank.of(rowIndex)

    val coordinate: String
        get() = (rowIndex + 65).toChar() + (colIndex + 1).toString()

    fun select() {
        if (!selected) {
            selected = true
            cellBackgroundColor = Color.YELLOW
            seatTotalPrice += rank.price
            return
        }
        if (selected) {
            selected = false
            cellBackgroundColor = Color.WHITE
            seatTotalPrice -= rank.price
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (rowIndex != other.rowIndex) return false
        if (colIndex != other.colIndex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rowIndex
        result = 31 * result + colIndex
        result = 31 * result + selected.hashCode()
        result = 31 * result + cellBackgroundColor.hashCode()
        return result
    }

    companion object {
        private val seats = mutableListOf<Seat>()

        fun of(
            rowIndex: Int,
            colIndex: Int,
        ): Seat {
            val seat = Seat(rowIndex, colIndex)
            return if (seat in seats) {
                return seats.first { it == seat }
            } else {
                seats.add(seat)
                seat
            }
        }
    }
}

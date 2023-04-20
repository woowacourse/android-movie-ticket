package woowacourse.movie.dto

import woowacourse.movie.R
import java.io.Serializable

class PositionDto(val row: Char, val col: Int) : Serializable {

    fun getPosition(): String {
        return "$row$col"
    }

    fun getColor(): Int {
        return when (row) {
            'A', 'B' -> R.color.b_seat_color
            'C', 'D' -> R.color.s_seat_color
            else -> R.color.a_seat_color
        }
    }

    companion object {
        fun of(row: Int, col: Int): PositionDto = PositionDto((row + 64).toChar(), col)
    }
}

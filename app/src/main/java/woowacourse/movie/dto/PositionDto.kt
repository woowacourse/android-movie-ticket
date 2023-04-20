package woowacourse.movie.dto

import java.io.Serializable

class PositionDto(val row: Char, val col: Int): Serializable {

    companion object {
        fun of(row: Int, col: Int): PositionDto = PositionDto((row + 64).toChar(), col)
    }
}

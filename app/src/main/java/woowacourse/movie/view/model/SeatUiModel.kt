package woowacourse.movie.view.model

import java.io.Serializable

class SeatUiModel(val row: Char, val col: Int) : UiModel, Serializable {
    companion object {
        fun toNumber(alphabet: Char): Int {
            return alphabet - 'A' + 1
        }

        fun toChar(rowNumber: Int): Char {
            return (rowNumber + 'A'.code).toChar()
        }
    }
}

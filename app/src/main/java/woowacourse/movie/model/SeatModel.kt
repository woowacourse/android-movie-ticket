package woowacourse.movie.model

import java.io.Serializable

data class SeatModel(
    val row: Int,
    val column: Int
) : Serializable {
    override fun toString(): String {
        return "${(row.convertToUpperLetter())}$column"
    }

    private fun Int.convertToUpperLetter(): Char = (this + 64).toChar()
}

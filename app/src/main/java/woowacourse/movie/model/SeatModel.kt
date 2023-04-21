package woowacourse.movie.model

data class SeatModel(
    val row: Int,
    val column: Int
) {
    override fun toString(): String {
        return "${(row.convertToUpperLetter())}$column"
    }

    private fun Int.convertToUpperLetter(): Char = (this + 64).toChar()
}

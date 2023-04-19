package woowacourse.movie.ui.seat

class Row private constructor(val value: Char) {
    companion object {
        private const val ALPHABET_A_NUMBER = 97

        fun createRows(size: Int): List<Row> = (ALPHABET_A_NUMBER..ALPHABET_A_NUMBER + size).map { Row(it.toChar()) }
    }
}

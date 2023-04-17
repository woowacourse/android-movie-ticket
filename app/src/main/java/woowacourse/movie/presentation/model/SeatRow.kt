package woowacourse.movie.presentation.model

class SeatRow(val value: Int) {
    override fun toString(): String = "${(value + 64).toChar()}"

    companion object {
        fun make(size: Int): List<SeatRow> = (1..size).map(::SeatRow)
    }
}

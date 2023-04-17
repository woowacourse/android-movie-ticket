package woowacourse.movie.presentation.model

class SeatColumn(private val value: Int) {
    override fun toString(): String = "$value"

    companion object {
        fun make(size: Int): List<SeatColumn> = (1..size).map(::SeatColumn)
    }
}

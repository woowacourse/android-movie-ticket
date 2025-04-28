package woowacourse.movie.domain

data class Seat(val seat: String) {
    private val column = seat[0]
    private val row = seat[1]

    init {
        validateColumn()
        validateRow()
    }

    private fun validateColumn() {
        if (column !in 'A'..'E') {
            throw IllegalArgumentException("존재하지 않는 행의 좌석입니다.")
        }
    }

    private fun validateRow() {
        if (row !in '1'..'5') {
            throw IllegalArgumentException("존재하지 않는 열의 좌석입니다.")
        }
    }

    fun price(): Int {
        return rate().price
    }

    private fun rate(): SeatRating {
        return when (column) {
            'C' -> SeatRating.S
            'D' -> SeatRating.S
            'E' -> SeatRating.A
            else -> SeatRating.B
        }
    }
}

package woowacourse.movie.domain.seat

class MovieSeatRow(override val row: Int) : SeatRow {
    override fun seatRankByRow(): SeatRank {
        return when (row) {
            0, 1 -> SeatRank.B
            2, 3 -> SeatRank.S
            4 -> SeatRank.A
            else -> throw IllegalStateException(ERROR_WRONG_SEAT_ROW.format(row))
        }
    }

    companion object {
        private const val ERROR_WRONG_SEAT_ROW = "좌석 열이 잘못 설정되었습니다. 설정된 열 : %d"
    }
}

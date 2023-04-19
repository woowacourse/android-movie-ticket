package woowacourse.movie.domain.seat

class MovieSeatRow(override val row: Int) : SeatRow {
    override fun SeatRankByRow(): SeatRank {
        return when (row) {
            1, 2 -> SeatRank.B
            3, 4 -> SeatRank.S
            5 -> SeatRank.A
            else -> SeatRank.EMPTY
        }
    }
}

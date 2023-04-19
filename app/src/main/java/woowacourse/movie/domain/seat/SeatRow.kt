package woowacourse.movie.domain.seat

interface SeatRow {
    val row: Int
    fun SeatRankByRow(): SeatRank
}

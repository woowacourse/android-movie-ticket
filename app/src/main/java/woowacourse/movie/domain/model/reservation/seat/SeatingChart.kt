package woowacourse.movie.domain.model.reservation.seat

class SeatingChart(
    val rowCount: Int = ROW_SEAT_COUNT,
    val colCount: Int = COL_SEAT_COUNT,
) {
    fun classifySeatByRow(
        row: Int,
        col: Int,
    ): Seat {
        validatePositionValue(row, col)
        return when (row) {
            in B_RANK_ROW -> Seat(row, col, SeatRank.B_RANK)
            in S_RANK_ROW -> Seat(row, col, SeatRank.S_RANK)
            in A_RANK_ROW -> Seat(row, col, SeatRank.A_RANK)
            else -> throw IllegalArgumentException("선택이 불가능한 좌석입니다.")
        }
    }

    private fun validatePositionValue(
        row: Int,
        col: Int,
    ) {
        require(
            row in MIN_POS_NUM until rowCount &&
                col in MIN_POS_NUM until colCount,
        ) { IllegalArgumentException("좌석의 범위를 벗어났습니다.") }
    }

    fun getSeatRankInfo(): List<IntRange> {
        return listOf(
            B_RANK_ROW,
            S_RANK_ROW,
            A_RANK_ROW,
        )
    }

    companion object {
        const val MIN_POS_NUM = 0
        const val ROW_SEAT_COUNT = 5
        const val COL_SEAT_COUNT = 4
        val B_RANK_ROW = (MIN_POS_NUM..1)
        val S_RANK_ROW = (2..3)
        val A_RANK_ROW = (4..4)
    }
}

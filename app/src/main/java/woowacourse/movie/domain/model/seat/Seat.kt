package woowacourse.movie.domain.model.seat

data class Seat private constructor(
    val row: Row,
    val col: Col,
) {
    val rank: SeatRank by lazy { SeatRank.withRow(row) }

    fun price(): Int {
        return rank.price
    }

    companion object {
        private const val ERROR_INVALID_COL_VALUE = "올바르지 않은 열 정보입니다."

        fun of(text: String): Seat {
            val rowValue = text[0]
            val colValue =
                text.substring(1).toIntOrNull() ?: throw IllegalArgumentException(
                    ERROR_INVALID_COL_VALUE,
                )
            return Seat(Row(rowValue), Col(colValue))
        }
    }
}

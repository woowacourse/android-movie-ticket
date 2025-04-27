package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.SeatType.entries

enum class SeatType {
    RANK_S,
    RANK_A,
    RANK_B,
    ;

    companion object {
        private const val ROW_RANK_A_RANGE = 5
        private val ROW_RANK_S_RANGE = 3..4
        private val ROW_RANK_B_RANGE = 1..2

        fun from(row: Int): SeatType =
            when (row) {
                ROW_RANK_A_RANGE -> RANK_A
                in ROW_RANK_S_RANGE -> RANK_S
                in ROW_RANK_B_RANGE -> RANK_B
                else -> throw IllegalArgumentException("[ERROR] 존재하지 않는 좌석 등급입니다.")
            }

        fun from(
            name: String,
            row: Int,
        ): SeatType = entries.find { it.name == name } ?: from(row)
    }
}

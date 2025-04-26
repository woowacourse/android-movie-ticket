package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.SeatType.entries

enum class SeatType {
    RANK_S,
    RANK_A,
    RANK_B,
    ;

    companion object {
        fun from(row: Int): SeatType =
            when (row) {
                5 -> RANK_A
                in 3..4 -> RANK_S
                in 1..2 -> RANK_B
                else -> throw IllegalArgumentException("[ERROR] 존재하지 않는 좌석 등급입니다.")
            }

        fun from(
            name: String,
            row: Int,
        ): SeatType = entries.find { it.name == name } ?: from(row)
    }
}

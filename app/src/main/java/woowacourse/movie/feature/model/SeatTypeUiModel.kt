package woowacourse.movie.feature.model

import woowacourse.movie.feature.model.SeatTypeUiModel.entries

enum class SeatTypeUiModel {
    RANK_S,
    RANK_A,
    RANK_B,
    ;

    companion object {
        fun from(name: String): SeatTypeUiModel = entries.find { it.name == name } ?: RANK_B
    }
}

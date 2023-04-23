package woowacourse.movie.ui.model.seat

import androidx.annotation.ColorRes
import woowacourse.movie.R
import woowacourse.movie.domain.seat.Rank

fun RankModel.mapToRank(): Rank {
    return when (this) {
        RankModel.A -> Rank.A
        RankModel.B -> Rank.B
        RankModel.S -> Rank.S
    }
}

fun Rank.mapToRankModel(): RankModel {
    return when (this) {
        Rank.A -> RankModel.A
        Rank.B -> RankModel.B
        Rank.S -> RankModel.S
    }
}

enum class RankModel(@ColorRes val color: Int) {
    A(R.color.a_rank),
    B(R.color.b_rank),
    S(R.color.s_rank),
}

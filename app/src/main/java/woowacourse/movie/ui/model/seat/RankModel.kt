package woowacourse.movie.ui.model.seat

import androidx.annotation.ColorRes
import woowacourse.movie.R
import woowacourse.movie.domain.seat.Rank

fun mapToRank(rank: RankModel): Rank {
    return when (rank) {
        RankModel.A -> Rank.A
        RankModel.B -> Rank.B
        RankModel.S -> Rank.S
    }
}

fun mapToRankModel(rank: Rank): RankModel {
    return when (rank) {
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

package woowacourse.movie.mapper

import com.example.domain.model.model.Rank

class RankMapper {
    fun map(row: Int): Rank {
        return when (row) {
            1, 2 -> Rank.B
            3, 4 -> Rank.S
            5 -> Rank.A
            else -> throw IllegalArgumentException(CANT_MATCHING_RANK_ERROR)
        }
    }

    companion object {
        const val CANT_MATCHING_RANK_ERROR = "해당 열에 해당하는 등급이 없습니다."
    }
}

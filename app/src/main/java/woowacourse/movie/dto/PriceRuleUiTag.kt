package woowacourse.movie.dto

import woowacourse.movie.domain.rules.PriceRule

enum class PriceRuleUiTag {
    RANK_A,
    RANK_S,
    RANK_B,
    ;

    companion object {
        fun find(priceRule: PriceRule): PriceRuleUiTag {
            return when (priceRule) {
                PriceRule.RANK_A -> RANK_A
                PriceRule.RANK_S -> RANK_S
                PriceRule.RANK_B -> RANK_B
            }
        }
    }
}

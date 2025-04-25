package woowacourse.movie.domain

import woowacourse.movie.domain.Seat.Companion.COLUMNS
import woowacourse.movie.domain.Seat.Companion.ROWS

class SeatsFactoryImpl : SeatsFactory {
    override fun get(): List<Seat> =
        ROWS.flatMap { row ->
            COLUMNS.map { column ->
                val locationString = "$row$column"
                Seat(
                    locationString,
                    ROW_TO_PRICE_RULE_MAP[row]
                        ?: throw IllegalArgumentException(ERR_UNKNOWN_RULE),
                )
            }
        }

    companion object {
        private val ROW_TO_PRICE_RULE_MAP: Map<String, PriceRule> =
            mapOf(
                "A" to PriceRule.RANK_A,
                "B" to PriceRule.RANK_A,
                "C" to PriceRule.RANK_S,
                "D" to PriceRule.RANK_S,
                "E" to PriceRule.RANK_B,
            )
        private const val ERR_UNKNOWN_RULE = "알 수 없는 규칙입니다."
    }
}

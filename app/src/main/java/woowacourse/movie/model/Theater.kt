package woowacourse.movie.model

import woowacourse.movie.model.pricing.Tier
import woowacourse.movie.model.seat.Position

class Theater(rows: Int, cols: Int) {
    private val positions: Set<Position> =
        (1..rows).flatMap { y ->
            (1..cols).map { x ->
                Position(x, y)
            }
        }.toSet()
    val tiers: Map<Position, Tier> =
        positions.map {
            val tier =
                when (it.y) {
                    3, 4 -> Tier.S
                    5 -> Tier.A
                    else -> Tier.B
                }
            Pair(it, tier)
        }.toMap()
}

package woowacourse.movie.data.remote.mapper

import woowacourse.movie.data.remote.dto.TierResponse
import woowacourse.movie.domain.Tier

object TierMapper {
    fun fromTierResponse(tierResponse: TierResponse): Tier {
        return when (tierResponse) {
            TierResponse.S -> {
                Tier.S
            }
            TierResponse.A -> {
                Tier.A
            }
            TierResponse.B -> {
                Tier.B
            }
        }
    }
}

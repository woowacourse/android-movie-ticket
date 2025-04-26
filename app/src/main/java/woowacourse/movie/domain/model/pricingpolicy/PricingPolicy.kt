package woowacourse.movie.domain.model.pricingpolicy

import java.io.Serializable

interface PricingPolicy : Serializable {
    fun calculatePrice(headCount: Int): Int
}
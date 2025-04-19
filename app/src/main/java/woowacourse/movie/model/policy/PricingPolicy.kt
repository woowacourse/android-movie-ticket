package woowacourse.movie.model.policy

import java.io.Serializable

interface PricingPolicy : Serializable {
    fun calculatePrice(headCount: Int): Int
}

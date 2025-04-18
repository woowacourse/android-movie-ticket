package woowacourse.movie.model

import java.io.Serializable

interface PricingPolicy : Serializable {
    fun calculatePrice(headCount: Int): Int
}

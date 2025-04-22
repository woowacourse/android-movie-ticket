package woowacourse.movie.domain.model

import java.io.Serializable

interface PricingPolicy : Serializable {
    fun calculatePrice(headCount: Int): Int
}
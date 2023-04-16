package woowacourse.movie.domain

import java.io.Serializable

data class Price(val value: Int = DEFAULT_PRICE) : Serializable {
    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}

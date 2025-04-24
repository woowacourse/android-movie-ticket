package woowacourse.movie.domain.model

import java.io.Serializable

data class Ticket(private val _price: Price) : Serializable {
    val price = _price.value
}

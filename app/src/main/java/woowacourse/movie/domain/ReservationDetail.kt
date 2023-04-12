package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetail(val date: LocalDateTime, val peopleCount: Int, val price: Price) :
    Serializable {
    fun getTotalPrice(): Int = peopleCount * price.value
}

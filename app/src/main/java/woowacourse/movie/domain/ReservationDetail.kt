package woowacourse.movie.domain

import java.time.LocalDateTime

data class ReservationDetail(val date: LocalDateTime, val peopleCount: Int, val price: Price) {
    fun getTotalPrice(): Int = peopleCount * price.value
}

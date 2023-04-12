package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

data class ReservationDetail(val date: LocalDate, val peopleCount: Int, val price: Price) :
    Serializable {
    fun getTotalPrice(): Int = peopleCount * price.value
}

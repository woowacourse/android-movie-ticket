package woowacourse.movie.model

import woowacourse.movie.model.theater.Theater
import java.io.Serializable

class Reservation(
    val theater: Theater,
    val ticketNum: Int,
) : Serializable {
    fun getCharge(): Int = theater.charge * ticketNum
}

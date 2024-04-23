package woowacourse.movie.model

import woowacourse.movie.model.screening.Screening
import java.io.Serializable

class Reservation(
    val screening: Screening,
    val ticketNum: Int,
) : Serializable {
    fun getCharge(): Int = screening.charge * ticketNum
}

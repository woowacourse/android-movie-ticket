package woowacourse.movie.model

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime

class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val count: Int,
) {
    val price: Int = TICKET_PRICE * count

    fun formatPrice(): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price.toLong())
    }

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

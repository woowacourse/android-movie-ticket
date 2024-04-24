package woowacourse.movie.model

import java.text.DecimalFormat

class MovieTicket(
    val title: String,
    val date: String,
    val time: String,
    val count: Int,
) {
    val price: Int = TICKET_PRICE * count

    fun getPrice(): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price.toLong())
    }

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

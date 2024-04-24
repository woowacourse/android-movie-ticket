package woowacourse.movie.model

class MovieTicket(
    val title: String,
    val date: String,
    val time: String,
    val count: Int,
) {
    val price: Int = TICKET_PRICE * count

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

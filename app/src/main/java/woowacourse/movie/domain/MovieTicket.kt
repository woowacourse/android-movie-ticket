package woowacourse.movie.domain

class MovieTicket(
    val title: String,
    val date: Long,
    val count: Int,
) {
    val price: Int = TICKET_PRICE * count

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

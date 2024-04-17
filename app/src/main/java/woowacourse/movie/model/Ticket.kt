package woowacourse.movie.model

class Ticket(val count: Int, private val price: Int = DEFAULT_PRICE) {
    fun amount(): Int = price * count

    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}

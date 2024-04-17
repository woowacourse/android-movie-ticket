package woowacourse.movie.model

class Ticket(val count: Int) {
    fun amount(): Int {
        return PRICE * count
    }

    companion object {
        private const val PRICE = 13000
    }
}

package woowacourse.movie.domain.model

class TicketCount(
    initialCount: Int = 0,
) {
    var value: Int = initialCount
        private set

    operator fun times(other: Int): Int = value * other

    fun increase(count: Int) {
        value += count
    }

    fun decrease(count: Int) {
        if (value - count > 0) {
            value -= count
        } else {
            value = 0
        }
    }
}

package woowacourse.movie.domain

class TicketCount(
    startCount: Int = 0,
) {
    var count: Int = startCount
        private set

    fun upCount() {
        count += 1
    }

    fun downCount() {
        count -= 1
        if (count < 0) count = 0
    }
}

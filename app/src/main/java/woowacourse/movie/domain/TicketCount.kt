package woowacourse.movie.domain

class TicketCount(
    private var count: Int = 0,
) {
    fun upCount(): Int {
        count += 1
        return count
    }

    fun downCount(): Int {
        count -= 1
        if (count < 0) count = 0
        return count
    }
}

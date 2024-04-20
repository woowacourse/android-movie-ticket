package woowacourse.movie.model

class ReservationCount(count: Int = COUNT_UNIT) {
    private var _count: Int = count
    val count: Int
        get() = _count

    fun plus() {
        _count++
    }

    fun minus() {
        val result = count - COUNT_UNIT
        _count = if (result <= COUNT_UNIT) COUNT_UNIT else result
    }

    companion object {
        private const val COUNT_UNIT = 1
    }
}

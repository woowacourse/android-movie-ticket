package woowacourse.movie.model

class ReservationCount {
    var count: Int = 1
        private set

    fun plus() {
        count++
    }

    fun minus() {
        val result = count - 1
        count = if (result <= 1) 1 else result
    }
}

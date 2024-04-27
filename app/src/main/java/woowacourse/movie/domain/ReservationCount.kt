package woowacourse.movie.domain

class ReservationCount {
    var count: Int = 1
        private set

    operator fun plus(increment: Int): ReservationCount {
        return ReservationCount().also { it.count = this.count + increment }
    }

    operator fun minus(decrement: Int): ReservationCount {
        return ReservationCount().also {
            val result = this.count - decrement
            it.count = if (result <= 1) 1 else result
        }
    }
}

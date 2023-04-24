package woowacourse.movie.domain

class Count(val value: Int) {
    operator fun plus(increment: Int): Count = Count(value + increment)

    operator fun minus(decrement: Int): Count {
        val result = value - decrement
        if (result <= 0)
            return Count(value)
        return Count(result)
    }
}

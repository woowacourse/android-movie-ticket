package woowacourse.movie.domain

class Count(val value: Int) {
    fun add(): Count = Count(value + COUNT_FACTOR)

    fun subtract(): Count {
        val result = value - COUNT_FACTOR
        if (result <= 0)
            return Count(value)
        return Count(result)
    }

    companion object {
        private const val COUNT_FACTOR = 1
    }
}

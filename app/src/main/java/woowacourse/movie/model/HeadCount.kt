package woowacourse.movie.model

@JvmInline
value class HeadCount(val value: Int) {
    fun plus(): HeadCount = HeadCount(value + 1)

    fun minus(): HeadCount {
        if (isValid()) {
            return HeadCount(value - 1)
        }
        return this
    }

    fun isValid(): Boolean = value > 0
}

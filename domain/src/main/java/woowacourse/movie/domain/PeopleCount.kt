package woowacourse.movie.domain

@JvmInline
value class PeopleCount(val value: Int = MINIMUM_COUNT) {
    fun minusCount(): PeopleCount {
        if (value == MINIMUM_COUNT) return this
        return PeopleCount(value - 1)
    }

    fun plusCount(): PeopleCount = PeopleCount(value + 1)

    companion object {
        const val MINIMUM_COUNT = 1
    }
}

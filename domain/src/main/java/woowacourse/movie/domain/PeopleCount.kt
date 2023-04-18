package woowacourse.movie.domain

@JvmInline
value class PeopleCount(val count: Int = MINIMUM_COUNT) {
    fun minusCount(): PeopleCount {
        if (count == MINIMUM_COUNT) return this
        return PeopleCount(count - 1)
    }

    fun plusCount(): PeopleCount = PeopleCount(count + 1)

    companion object {
        const val MINIMUM_COUNT = 1
    }
}

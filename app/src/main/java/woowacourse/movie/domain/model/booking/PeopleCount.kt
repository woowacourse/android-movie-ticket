package woowacourse.movie.domain.model.booking

@JvmInline
value class PeopleCount(val value: Int = MIN_PEOPLE_COUNT) {
    fun increase(limit: Int): PeopleCount {
        val increasedCount = this.value + PEOPLE_COUNT_CHANGE_STANDARD
        return PeopleCount(increasedCount.coerceAtMost(limit))
    }

    fun decrease(): PeopleCount {
        val decreasedCount = this.value - PEOPLE_COUNT_CHANGE_STANDARD
        return PeopleCount((decreasedCount).coerceAtLeast(MIN_PEOPLE_COUNT))
    }

    companion object {
        private const val PEOPLE_COUNT_CHANGE_STANDARD = 1
        private const val MIN_PEOPLE_COUNT = 1
    }
}

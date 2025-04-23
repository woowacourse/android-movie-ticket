package woowacourse.movie.domain.model

@JvmInline
value class PeopleCount(val value: Int = MIN_PEOPLE_COUNT) {
    fun increase(): PeopleCount = PeopleCount(this.value + PEOPLE_COUNT_CHANGE_STANDARD)

    fun decrease(): PeopleCount = PeopleCount((this.value - PEOPLE_COUNT_CHANGE_STANDARD).coerceAtLeast(MIN_PEOPLE_COUNT))

    companion object {
        private const val PEOPLE_COUNT_CHANGE_STANDARD = 1
        private const val MIN_PEOPLE_COUNT = 1
    }
}

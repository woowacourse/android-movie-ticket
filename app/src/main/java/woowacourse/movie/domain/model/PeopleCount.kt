package woowacourse.movie.domain.model

@JvmInline
value class PeopleCount(val value: Int = MIN_PEOPLE_COUNT) {
    fun increase(): PeopleCount = PeopleCount(this.value + 1)

    fun decrease(): PeopleCount = PeopleCount(this.value - 1)

    fun canDecrease(): Boolean = value - 1 >= MIN_PEOPLE_COUNT

    companion object {
        private const val MIN_PEOPLE_COUNT = 1
    }
}

package woowacourse.movie.domain.seat

@JvmInline
value class Column(val value: Int) {

    init {
        require(value in MINIMUM..MAXIMUM)
    }

    companion object {
        const val MINIMUM = 1
        const val MAXIMUM = 4

        fun of(number: Int): Column = Column(number + MINIMUM)
    }
}

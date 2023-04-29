package woowacourse.movie.domain.seat

/**
 * 1..4에 해당하는 숫자를 가집니다.
 */
@JvmInline
value class Column(val value: Int) {

    init {
        require(value in MINIMUM..MAXIMUM)
    }

    override fun toString(): String = value.toString()
    companion object {
        const val MINIMUM = 1
        const val MAXIMUM = 4

        const val INDEX_MINIMUM = 0
        const val INDEX_MAXIMUM = 3

        /**
         * 문자 1..5로 표현된 숫자를
         * 숫자 0..4로 표현된 숫자로 변경합니다.
         */
        fun of(number: Int): Column = Column(number + MINIMUM)
    }
}

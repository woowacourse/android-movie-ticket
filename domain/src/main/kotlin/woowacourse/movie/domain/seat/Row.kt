package woowacourse.movie.domain.seat

/**
 * A..E에 해당하는 문자를 가집니다.
 */
@JvmInline
value class Row(val value: Char) {

    init {
        require(value in MINIMUM..MAXIMUM)
    }

    override fun toString(): String = value.toString()
    companion object {
        const val MINIMUM = 'A'
        const val MAXIMUM = 'E'

        const val INDEX_MINIMUM = 0
        const val INDEX_MAXIMUM = 4

        /**
         * 숫자 0..4를 받아 문자로 저장합니다.
         */
        fun of(number: Int): Row =
            Row(MINIMUM + number)
    }
}

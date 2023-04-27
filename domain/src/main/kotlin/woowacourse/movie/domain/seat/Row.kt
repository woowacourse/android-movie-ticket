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

        /**
         * 문자 A..E로 표현된 행을
         * 숫자 0..4로 표현된 행으로 변경합니다.
         */
        fun toNumber(row: Char): Int = row - MINIMUM

        /**
         * 숫자 0..4를 받아 문자로 저장합니다.
         */
        fun of(number: Int): Row =
            Row(number.toChar() + 'A'.toInt())
    }
}

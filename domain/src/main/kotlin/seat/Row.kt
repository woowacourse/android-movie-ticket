package seat

@JvmInline
value class Row(val value: Char) {

    init {
        require(value in MINIMUM..MAXIMUM)
    }

    override fun toString(): String = value.toString()

    companion object {
        const val MINIMUM = 'A'
        const val MAXIMUM = 'E'

        fun toNumber(row: Char): Int = row - MINIMUM

        fun of(number: Int): Row =
            Row(number.toChar() + 'A'.toInt())
    }
}

package woowacourse.movie.ui.seatreservation.uimodel

@JvmInline
value class Money private constructor(
    val value: Int,
) {

    companion object {
        fun from(amount: Int): Money = Money(amount)
    }
}

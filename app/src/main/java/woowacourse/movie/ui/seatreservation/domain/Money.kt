package woowacourse.movie.ui.seatreservation.domain

@JvmInline
value class Money private constructor(
    val value: Int,
) {

    companion object {
        fun from(amount: Int): Money = Money(amount)
    }
}

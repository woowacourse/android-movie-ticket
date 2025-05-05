package woowacourse.movie.domain.model

@JvmInline
value class TicketPrice(
    val value: Int,
) {
    companion object {
        fun from(seatType: SeatType): TicketPrice =
            when (seatType) {
                SeatType.RANK_S -> TicketPrice(15_000)
                SeatType.RANK_A -> TicketPrice(12_000)
                SeatType.RANK_B -> TicketPrice(10_000)
            }
    }
}

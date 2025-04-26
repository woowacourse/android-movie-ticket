package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.screen.SeatType

class DiceCinemaPricePolicy : PricePolicy {
    override fun calculatePrice(seatType: SeatType): Int =
        when (seatType) {
            SeatType.S_CLASS -> 15_000
            SeatType.A_CLASS -> 12_000
            SeatType.B_CLASS -> 10_000
        }
}

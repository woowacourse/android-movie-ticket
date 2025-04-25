package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.seat.SeatType

interface PricePolicy {
    fun calculatePrice(seatType: SeatType): Int
}

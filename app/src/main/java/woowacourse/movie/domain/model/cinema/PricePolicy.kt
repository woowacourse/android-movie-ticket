package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.screen.SeatType

interface PricePolicy {
    fun calculatePrice(seatType: SeatType): Int
}

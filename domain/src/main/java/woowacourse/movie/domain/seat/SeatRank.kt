package woowacourse.movie.domain.seat

import woowacourse.movie.domain.Price

enum class SeatRank(
    val price: Price
) {
    B(Price(10_000)),
    S(Price(15_000)),
    A(Price(12_000)),
}

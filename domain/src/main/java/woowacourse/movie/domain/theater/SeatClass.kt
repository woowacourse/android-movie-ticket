package woowacourse.movie.domain.theater

import woowacourse.movie.domain.discount.Money

enum class SeatClass(val seatFee: Money) {
    S(Money(15_000)), A(Money(12_000)), B(Money(10_000))
}

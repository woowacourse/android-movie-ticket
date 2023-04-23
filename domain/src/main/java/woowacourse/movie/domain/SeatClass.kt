package woowacourse.movie.domain

enum class SeatClass(val seatFee: Money) {
    S(Money(15_000)), A(Money(12_000)), B(Money(10_000))
}

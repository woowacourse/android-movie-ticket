package domain.seatPolicy

import domain.Price

enum class SeatRank(val price: Price) {
    S(Price(15_000)),
    A(Price(12_000)),
    B(Price(10_000))
}

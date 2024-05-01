package woowacourse.movie.model

enum class SeatRate(val price: Price) {
    S(Price(15_000)),
    A(Price((12_000))),
    B(Price(10_000)),
}

package woowacourse.movie.domain.model.reservation.seat

enum class SeatRank(val price: Int) {
    B_RANK(10_000),
    A_RANK(12_000),
    S_RANK(15_000),
}

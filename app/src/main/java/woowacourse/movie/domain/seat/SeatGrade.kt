package woowacourse.movie.domain.seat

enum class SeatGrade(
    val price: Int
) {
    B(10_000),
    S(15_000),
    A(12_000);
}

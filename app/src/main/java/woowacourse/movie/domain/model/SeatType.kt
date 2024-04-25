package woowacourse.movie.domain.model

enum class SeatType(
    val price: Int,
){
    B(10_000),
    S(15_000),
    A(12_000);
}

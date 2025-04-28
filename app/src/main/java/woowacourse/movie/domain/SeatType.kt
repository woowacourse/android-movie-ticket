package woowacourse.movie.domain

enum class SeatType(
    val price: Int,
    val color: String,
) {
    B(10000, "#8E13EF"),
    A(12000, "#19D358"),
    S(15000, "#1B48E9"),
}

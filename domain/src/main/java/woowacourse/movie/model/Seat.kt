package woowacourse.movie.model

data class Seat(
    val rate: SeatRate,
    val row: Int,
    val col: Int,
) {
    val price = rate.price.price
}

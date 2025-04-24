package woowacourse.movie.model

@JvmInline
value class Seats(
    private val seats: List<Seat>,
) {
    val totalPrice: Int
        get() = seats.sumOf { it.price }

    fun labels(): List<String> = seats.map { it.label }
}

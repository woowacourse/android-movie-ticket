package woowacourse.movie.domain.model

data class Reservation(
    val id: Int,
    val screen: Screen,
    val count: Int,
) {
    val totalPrice: Int
        get() = count * screen.price
}

package woowacourse.movie.selectSeat

enum class SeatPrice(
    private val price: Int,
) {
    A(10000),
    B(10000),
    C(15000),
    D(15000),
    E(12000),
    ;

    companion object {
        fun getPrice(seatTag: String): Int {
            entries.forEach {
                if (seatTag[0].toString() == it.name) return it.price
            }
            return 0
        }
    }
}

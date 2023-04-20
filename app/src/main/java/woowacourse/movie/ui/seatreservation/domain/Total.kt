package woowacourse.movie.ui.seatreservation.domain

class Total {
    var price: Int = 0
        private set

    fun calculate(selectSeatPrice: Money) {
        price += selectSeatPrice.value
    }

    companion object {
        fun from() = Total()
    }
}

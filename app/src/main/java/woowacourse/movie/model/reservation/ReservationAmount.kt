package woowacourse.movie.model.reservation

class ReservationAmount(val amount: Int = INITIALIZE_AMOUNT) {
    init {
        require(amount >= 0) { NEGATIVE_NUMBER_MESSAGE }
    }

    operator fun plus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount + value.amount)
    }

    operator fun minus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount - value.amount)
    }

    companion object {
        private const val INITIALIZE_AMOUNT = 0
        private const val NEGATIVE_NUMBER_MESSAGE = "예매 금액은 0보다 작을 수 없습니다."
    }
}

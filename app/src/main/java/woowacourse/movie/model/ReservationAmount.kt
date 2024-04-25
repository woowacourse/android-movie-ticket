package woowacourse.movie.model

class ReservationAmount(val amount: Int) {
    operator fun plus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(value.amount + amount)
    }
}

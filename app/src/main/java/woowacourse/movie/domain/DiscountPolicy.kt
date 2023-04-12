package woowacourse.movie.domain

interface DiscountPolicy {
    fun discount(reservationDetail: ReservationDetail): ReservationDetail
}

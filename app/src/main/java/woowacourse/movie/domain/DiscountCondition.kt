package woowacourse.movie.domain

interface DiscountCondition {
    fun isSatisfiedBy(reservation: Reservation): Boolean
}

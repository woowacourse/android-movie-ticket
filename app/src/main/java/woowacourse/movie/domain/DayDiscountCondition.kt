package woowacourse.movie.domain

class DayDiscountCondition(private val days: List<Int>) : DiscountCondition {
    override fun isSatisfiedBy(reservation: Reservation): Boolean {
        return reservation.screeningDateTime.dayOfMonth in days
    }
}

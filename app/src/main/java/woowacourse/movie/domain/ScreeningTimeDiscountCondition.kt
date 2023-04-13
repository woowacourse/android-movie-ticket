package woowacourse.movie.domain

class ScreeningTimeDiscountCondition(private val timeRanges: List<TimeRange>) : DiscountCondition {
    override fun isSatisfiedBy(reservation: Reservation): Boolean {
        return timeRanges.any { reservation.screeningDateTime.toLocalTime() in it }
    }
}

package woowacourse.movie.domain

class ScreeningTimeDiscountCondition(private val timeRanges: List<TimeRange>) : DiscountCondition {
    override fun isSatisfiedBy(reservation: Reservation): Boolean =
        timeRanges.any { reservation.screeningDateTime.toLocalTime() in it }
}

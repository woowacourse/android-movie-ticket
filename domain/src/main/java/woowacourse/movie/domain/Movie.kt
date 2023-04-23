package woowacourse.movie.domain

class Movie(
    val id: Long,
    val title: String,
    val runningTime: Minute,
    val summary: String
) {
    private val _screeningInfos: MutableSet<ScreeningInfoOfMovie> = mutableSetOf()
    val screeningInfos: Set<ScreeningInfoOfMovie>
        get() = _screeningInfos.toSet()

    fun addScreening(screeningInfo: ScreeningInfoOfMovie) {
        _screeningInfos.add(screeningInfo)
    }

    fun reserve(screeningInfo: ScreeningInfoOfMovie, seatPoints: Set<Point>): ReservationResult {
        require(screeningInfo in _screeningInfos) { NOT_EXIST_SCREENING_INFO_ERROR }
        require(screeningInfo.canBeReserved(seatPoints)) { CAN_NOT_BE_RESERVED_ERROR }

        val fee = seatPoints.sumOf {
            val initFee = screeningInfo.getInitFee(it)
            DiscountApplicator.applyDiscount(screeningInfo.screeningDateTime, initFee)
        }

        return ReservationResult(id, screeningInfo, seatPoints, fee)
    }

    companion object {
        private const val NOT_EXIST_SCREENING_INFO_ERROR = "예매하려는 상영이 존재하지 않습니다."
        private const val CAN_NOT_BE_RESERVED_ERROR = "해당 좌석을 예매할 수 없습니다."
    }
}

private fun Collection<Point>.sumOf(selector: (Point) -> Money): Money {
    var sum = Money(0)
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

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
        require(screeningInfo.canBeReserved(seatPoints)) { NOT_EXIST_SEAT_ERROR }

        val fee = calculateReservationFee(screeningInfo, seatPoints)

        return ReservationResult(id, screeningInfo, seatPoints, fee)
    }

    fun calculateReservationFee(screeningInfo: ScreeningInfoOfMovie, seatPoints: Set<Point>): Money {
        require(screeningInfo in _screeningInfos) { NOT_EXIST_SCREENING_INFO_ERROR }
        require(screeningInfo.canBeReserved(seatPoints)) { NOT_EXIST_SEAT_ERROR }

        return seatPoints.sumOf {
            val initFee = screeningInfo.getInitFee(it)
            DiscountApplicator.applyDiscount(screeningInfo.screeningDateTime, initFee)
        }
    }

    companion object {
        private const val NOT_EXIST_SCREENING_INFO_ERROR = "상영이 존재하지 않습니다."
        private const val NOT_EXIST_SEAT_ERROR = "좌석이 존재하지 않습니다."
    }
}

private fun Collection<Point>.sumOf(selector: (Point) -> Money): Money {
    var sum = Money(0)
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

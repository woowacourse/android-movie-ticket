package woowacourse.movie.domain.model.reservation

@JvmInline
value class ReservationCount(
    val value: Int = RESERVATION_MIN_COUNT,
) {
    init {
        require(value >= RESERVATION_MIN_COUNT) { INVALID_RESERVATION_COUNT_MESSAGE }
    }

    operator fun plus(other: Int): ReservationCount = ReservationCount(value + other)

    operator fun minus(other: Int): ReservationCount = ReservationCount(value - other)

    companion object {
        const val RESERVATION_MIN_COUNT = 1
        private const val INVALID_RESERVATION_COUNT_MESSAGE = "최소 예매 인원은 ${RESERVATION_MIN_COUNT}명 입니다"
    }
}

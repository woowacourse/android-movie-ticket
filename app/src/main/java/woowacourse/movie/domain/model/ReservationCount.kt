package woowacourse.movie.domain.model

@JvmInline
value class ReservationCount(
    val count: Int = MINIMUM_RESERVATION_COUNT,
) {
    init {
        require(count >= MINIMUM_RESERVATION_COUNT) { INVALID_RESERVATION_COUNT }
    }

    operator fun plus(other: Int): ReservationCount = ReservationCount(count + other)

    operator fun minus(other: Int): ReservationCount = ReservationCount(count - other)

    companion object {
        const val MINIMUM_RESERVATION_COUNT = 1
        private const val INVALID_RESERVATION_COUNT = "최소 예약 인원은 ${MINIMUM_RESERVATION_COUNT}명 이상이어야 합니다"
    }
}

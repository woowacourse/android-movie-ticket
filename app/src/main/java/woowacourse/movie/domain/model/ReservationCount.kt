package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class ReservationCount(
    val value: Int = MINIMUM_RESERVATION_COUNT,
) : Parcelable {
    init {
        require(value >= MINIMUM_RESERVATION_COUNT) { INVALID_RESERVATION_COUNT }
    }

    operator fun plus(other: Int): ReservationCount = ReservationCount(value + other)

    operator fun minus(other: Int): ReservationCount = ReservationCount(value - other)

    companion object {
        const val MINIMUM_RESERVATION_COUNT = 1
        private const val INVALID_RESERVATION_COUNT = "최소 예약 인원은 ${MINIMUM_RESERVATION_COUNT}명 이상이어야 합니다"
    }
}

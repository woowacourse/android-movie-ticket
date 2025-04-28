package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ReservationInfo
import java.time.LocalDateTime

@Parcelize
class ReservationInfoUiModel(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: Int,
    val seats: List<SeatUiModel>,
) : Parcelable

fun ReservationInfo.toUiModel(): ReservationInfoUiModel =
    ReservationInfoUiModel(
        title,
        reservationDateTime,
        reservationCount.value,
        seats.map { it.toUiModel() },
    )

fun ReservationInfoUiModel.toModel(): ReservationInfo {
    val info =
        ReservationInfo(
            title,
            reservationDateTime,
            ReservationCount(reservationCount),
        )

    this.seats.forEach { info.updateSeats(it.toModel()) }

    return info
}

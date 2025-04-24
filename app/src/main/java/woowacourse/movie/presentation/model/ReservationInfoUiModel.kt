package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import java.time.LocalDateTime

@Parcelize
class ReservationInfoUiModel(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: Int,
    val totalPrice: Int,
) : Parcelable

fun ReservationInfo.toUiModel(): ReservationInfoUiModel =
    ReservationInfoUiModel(
        title,
        reservationDateTime,
        reservationCount.value,
        totalPrice(),
    )

fun ReservationInfoUiModel.toModel(): ReservationInfo =
    ReservationInfo(
        title,
        reservationDateTime,
        ReservationCount(reservationCount),
    )

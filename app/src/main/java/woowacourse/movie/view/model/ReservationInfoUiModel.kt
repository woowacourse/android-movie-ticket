package woowacourse.movie.view.model

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
) : Parcelable

fun ReservationInfo.toUiModel(): ReservationInfoUiModel =
    ReservationInfoUiModel(
        title,
        reservationDateTime,
        reservationCount.value,
    )

fun ReservationInfoUiModel.toModel(): ReservationInfo =
    ReservationInfo(
        title,
        reservationDateTime,
        ReservationCount(reservationCount),
    )

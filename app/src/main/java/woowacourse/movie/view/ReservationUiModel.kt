package woowacourse.movie.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Reservation
import java.time.LocalDateTime

fun Reservation.toUiModel(): ReservationUiModel = ReservationUiModel(
    movieTitle,
    screeningDateTime,
    peopleCount,
    totalReservationFee.amount
)

@Parcelize
data class ReservationUiModel(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val peopleCount: Int,
    val totalReservationFee: Int
) : Parcelable

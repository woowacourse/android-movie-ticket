package woowacourse.movie.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.domain.Reservation
import java.time.LocalDateTime

fun Reservation.toUiModel(): ReservationUiModel = ReservationUiModel(
    movieTitle,
    screeningDateTime,
    seats.size,
    seats.map { it.toUi() },
    finalReservationFee.amount
)

@Parcelize
data class ReservationUiModel(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val peopleCount: Int,
    val seats: List<String>,
    val finalReservationFee: Int
) : Parcelable

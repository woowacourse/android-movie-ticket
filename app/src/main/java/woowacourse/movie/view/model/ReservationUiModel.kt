package woowacourse.movie.view.model

import android.os.Parcelable
import com.example.domain.Reservation
import kotlinx.parcelize.Parcelize
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

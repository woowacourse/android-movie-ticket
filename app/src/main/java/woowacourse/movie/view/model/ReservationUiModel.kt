package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationUiModel(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val peopleCount: Int,
    val seats: List<String>,
    val finalReservationFee: Int
) : Parcelable

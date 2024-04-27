package woowacourse.movie.presentation.reservation.booking.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class SeatSelectionNavArgs(
    val screenMovieId: Long = -1,
    val movieTitle: String = "",
    val headCount: Int = 1,
    val selectedDateTime: LocalDateTime = LocalDateTime.now()
) : Parcelable
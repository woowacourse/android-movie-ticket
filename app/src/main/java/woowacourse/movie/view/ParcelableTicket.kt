package woowacourse.movie.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ParcelableTicket(
    val movie: ParcelableMovie,
    val showtime: LocalDateTime,
    val count: Int,
) : Parcelable

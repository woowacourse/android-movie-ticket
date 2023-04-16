package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Reservation(
    val movie: Movie,
    val dateTime: LocalDateTime,
    val ticket: Ticket
) : Parcelable

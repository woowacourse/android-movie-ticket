package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Parcelable

package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class BookedMovie(
    val movieId: Long,
    val theaterId: Long,
    val ticketCount: Int,
    val bookedDateTime: LocalDateTime,
) : Parcelable

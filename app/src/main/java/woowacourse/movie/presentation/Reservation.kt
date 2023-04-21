package woowacourse.movie.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Reservation(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Parcelable

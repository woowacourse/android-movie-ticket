package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationInfo(
    val ticket: Ticket,
    val total: String,
    val seat: List<String>,
) : Parcelable

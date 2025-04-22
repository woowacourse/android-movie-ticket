package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketInfo(
    val movie: Movie,
    val date: String,
    val time: String,
    val quantity: TicketQuantity,
) : Parcelable

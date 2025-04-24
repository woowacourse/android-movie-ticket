package woowacourse.movie.booking.complete

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingCompleteUiModel(
    val title: String,
    val date: String,
    val time: String,
    val ticketQuantity: Int,
    val ticketTotalPrice: Int,
) : Parcelable

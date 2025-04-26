package woowacourse.movie.booking.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketUiModel(
    val title: String,
    val headCount: Int,
    val selectedDateText: String,
    val selectedTimeText: String,
    val totalPrice: String,
    val seats: String,
) : Parcelable

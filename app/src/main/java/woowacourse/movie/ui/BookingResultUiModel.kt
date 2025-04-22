package woowacourse.movie.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingResultUiModel(
    val title: String,
    val headCount: String,
    val selectedDateText: String,
    val selectedTimeText: String,
    val bookingAmountText: String,
) : Parcelable

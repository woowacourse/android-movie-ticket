package woowacourse.movie.booking.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingDetailUiModel(
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTimeText: String,
    val posterResId: Int,
) : Parcelable

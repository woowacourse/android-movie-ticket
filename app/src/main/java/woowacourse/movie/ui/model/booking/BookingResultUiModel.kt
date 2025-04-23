package woowacourse.movie.ui.model.booking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingResultUiModel(
    val title: String,
    val headCount: String,
    val selectedDate: String,
    val selectedTime: String,
) : Parcelable

package woowacourse.movie.presentation.reservation.booking.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenDateTimeUiModel(
    val date: String = "",
    val times: List<String> = emptyList(),
) : Parcelable

package woowacourse.movie.presentation.reservation.booking.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.HeadCount

@Parcelize
data class MovieReservationUiState(
    val movie: ScreeningMovieUiModel = ScreeningMovieUiModel(),
    val id: Long = -1,
    private val count: Int = 1,
    val screenDateTimes: List<ScreenDateTimeUiModel> = emptyList(),
    val selectedDate: ScreenDateTimeUiModel = ScreenDateTimeUiModel(),
    val selectedTime: String = "",
) : Parcelable {
    val selectedTimePosition: Int
        get() = screenTimes.indexOf(selectedTime)

    val selectedDatePosition: Int
        get() = screenDates.indexOf(selectedDate.date)

    val screenDates: List<String>
        get() = screenDateTimes.map { it.date }

    val screenTimes: List<String>
        get() = selectedDate.times

    @IgnoredOnParcel
    val headCount: HeadCount = HeadCount(count)
}

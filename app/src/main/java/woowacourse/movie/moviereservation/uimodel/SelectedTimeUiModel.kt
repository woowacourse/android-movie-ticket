package woowacourse.movie.moviereservation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Parcelize
data class SelectedTimeUiModel(
    val position: Int = DEFAULT_POSITION,
    val time: String,
) : Parcelable {
    init {
        isValidTime(time)
    }

    constructor(timePosition: Int = DEFAULT_POSITION, time: LocalTime) : this(
        timePosition,
        time.format(
            formatter,
        ),
    )

    private fun isValidTime(date: String): Boolean = Regex("\\b([01]?[0-9]|2[0-3]):[0-5][0-9]\\b").matches(date)

    fun getLocalTime(): LocalTime = LocalTime.parse(time, formatter)

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("HH:mm")
        private const val DEFAULT_POSITION = 0
    }
}

package woowacourse.movie.moviereservation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class SelectedDateUiModel(
    val position: Int = DEFAULT_POSITION,
    val date: String,
) : Parcelable {
    init {
        isValidDate(date)
    }

    constructor(datePosition: Int = DEFAULT_POSITION, date: LocalDate) : this(
        datePosition,
        date.format(
            formatter,
        ),
    )

    private fun isValidDate(date: String): Boolean = Regex("\\d{4}-\\d{2}-\\d{2}").matches(date)

    fun getLocalDate(): LocalDate = LocalDate.parse(date, formatter)

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private const val DEFAULT_POSITION = 0
    }
}

package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ui.movielist.model.ItemUI
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class MovieUI(
    val id: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
    @DrawableRes val thumbnail: Int?,
) : Parcelable, ItemUI {
    val LocalDate.formattedDate: String
        get() = this.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN))

    companion object {
        private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"
    }
}

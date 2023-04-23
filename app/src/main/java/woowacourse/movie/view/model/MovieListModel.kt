package woowacourse.movie.view.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.temporal.ChronoUnit

sealed class MovieListModel {

    data class MovieAdModel(
        @DrawableRes val banner: Int,
        val url: String
    ) : MovieListModel()

    @Parcelize
    data class MovieUiModel(
        val title: String,
        val screeningStartDate: LocalDate,
        val screeningEndDate: LocalDate,
        val runningTime: Int,
        val posterResourceId: Int,
        val summary: String
    ) : Parcelable, MovieListModel() {
        fun getAllScreeningDates(): List<LocalDate> {
            val screeningDates = mutableListOf<LocalDate>()
            var screeningDate = screeningStartDate
            repeat(ChronoUnit.DAYS.between(screeningStartDate, screeningEndDate).toInt() + 1) {
                screeningDates.add(screeningDate)
                screeningDate = screeningDate.plusDays(1)
            }
            return screeningDates
        }
    }
}

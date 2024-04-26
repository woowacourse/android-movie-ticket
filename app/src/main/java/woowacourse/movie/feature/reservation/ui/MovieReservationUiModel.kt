package woowacourse.movie.feature.reservation.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieReservationUiModel(
    val posterImageDrawable: Drawable?,
    val titleMessage: String,
    val screeningDateMessage: String,
    val runningTimeMessage: String,
    val synopsisMessage: String,
) {
    companion object {
        fun of(
            context: Context,
            movie: Movie,
        ): MovieReservationUiModel {
            return MovieReservationUiModel(
                ContextCompat.getDrawable(context, movie.posterImageId),
                movie.title,
                screeningDateMessage(context, movie.startScreeningDate, movie.endScreeningDate),
                runningTimeMessage(context, movie.runningTime),
                movie.synopsis,
            )
        }

        private fun screeningDateMessage(
            context: Context,
            startScreeningDate: LocalDate,
            endScreeningDate: LocalDate,
        ): String {
            return context.resources.getString(R.string.screening_date)
                .format(startScreeningDate.message(), endScreeningDate.message())
        }

        private fun LocalDate.message() = format(DateTimeFormatter.ofPattern("yyyy.M.d"))

        private fun runningTimeMessage(
            context: Context,
            runningTime: Int,
        ): String {
            return context.getString(R.string.running_time).format(runningTime)
        }
    }
}

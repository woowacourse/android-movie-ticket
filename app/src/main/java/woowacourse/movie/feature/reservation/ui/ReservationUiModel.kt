package woowacourse.movie.feature.reservation.ui

import android.content.Context
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationUiModel(
    @DrawableRes
    val posterImageId: Int,
    val titleMessage: String,
    val screeningDateMessage: String,
    val runningTimeMessage: String,
    val synopsisMessage: String,
) {
    companion object {
        fun of(
            context: Context,
            movie: Movie,
        ): ReservationUiModel {
            return ReservationUiModel(
                movie.posterImageId,
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
            return context.resources.getString(
                R.string.screening_date,
                startScreeningDate.message(),
                endScreeningDate.message(),
            )
        }

        private fun LocalDate.message() = format(DateTimeFormatter.ofPattern("yyyy.M.d"))

        private fun runningTimeMessage(
            context: Context,
            runningTime: Int,
        ): String {
            return context.resources.getString(R.string.running_time, runningTime)
        }
    }
}

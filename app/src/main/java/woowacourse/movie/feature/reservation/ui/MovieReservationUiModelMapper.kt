package woowacourse.movie.feature.reservation.ui

import android.content.Context
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.MovieContent
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun MovieContent.toReservationUiModel(context: Context): MovieReservationUiModel {
    return MovieReservationUiModel(
        ContextCompat.getDrawable(context, posterImageId),
        title,
        screeningDateMessage(context, screeningDate),
        runningTimeMessage(context, runningTime),
        synopsis,
    )
}

private fun screeningDateMessage(
    context: Context,
    screeningDate: LocalDate,
): String {
    return context.resources.getString(R.string.screening_date)
        .format(screeningDate.format(DateTimeFormatter.ofPattern("yyyy.M.d")))
}

private fun runningTimeMessage(
    context: Context,
    runningTime: Int,
): String {
    return context.getString(R.string.running_time).format(runningTime)
}

package woowacourse.movie.feature.home.ui

import android.content.Context
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun List<Movie>.toMovieListUiModels(context: Context): List<MovieListUiModel> {
    return map { it.toMovieListUiModel(context) }
}

private fun Movie.toMovieListUiModel(context: Context): MovieListUiModel {
    return MovieListUiModel(
        ContextCompat.getDrawable(context, posterImageId),
        title,
        screeningDateMessage(context, startScreeningDate, endScreeningDate),
        runningTimeMessage(context, runningTime),
        id,
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
    return context.resources.getString(R.string.running_time).format(runningTime)
}

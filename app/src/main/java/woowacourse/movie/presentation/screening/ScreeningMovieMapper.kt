package woowacourse.movie.presentation.screening

import woowacourse.movie.model.date.ScreeningMovie
import java.time.format.DateTimeFormatter

private const val DATE_PATTERN = "yyyy.MM.dd"
private val DateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

fun ScreeningMovie.toScreenMovieUiModel(): ScreeningMovieUiModel {
    val screenStartDate: String =
        screenDateTimes.first().date.format(DateFormatter)
    val screenEndDate: String =
        screenDateTimes.last().date.format(DateFormatter)
    val runningTime = movie.runningTime.time.inWholeMinutes
    return ScreeningMovieUiModel(
        id = id,
        title = movie.title,
        screenDate = "상영일: $screenStartDate ~ $screenEndDate",
        runningTime = "러닝타임: ${runningTime}분",
    )
}

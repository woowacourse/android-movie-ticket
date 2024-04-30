package woowacourse.movie.screeningmovie

import woowacourse.movie.model.Advertisement
import woowacourse.movie.model.ScreenView
import woowacourse.movie.model.ScreeningMovie
import java.time.format.DateTimeFormatter

fun ScreeningMovie.toScreenMovieUiModel(): ScreenMovieUiModel {
    val pattern = "yyyy.MM.dd"
    val screenDate: String =
        screenDateTimes.first().date.format(DateTimeFormatter.ofPattern(pattern))
    val runningTime = movie.runningTime.time.inWholeMinutes
    return ScreenMovieUiModel(
        id = id,
        title = movie.title,
        screenDate = "러닝타임: ${runningTime}분",
        runningTime = "상영일: $screenDate",
    )
}

fun List<ScreenView>.toScreenItems(): List<ScreeningItem> =
    this.map { view ->
        when (view) {
            is ScreeningMovie -> view.toScreenMovieUiModel()
            is Advertisement -> AdvertiseUiModel()
        }
    }

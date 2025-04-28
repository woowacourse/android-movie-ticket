package woowacourse.movie.model

import woowacourse.movie.uiModel.MovieInfoUIModel
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MovieInfo(
    val posterKey: String,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Duration,
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")

        fun fromUiModel(dto: woowacourse.movie.uiModel.MovieInfoUIModel): woowacourse.movie.model.MovieInfo =
            MovieInfo(
                posterKey = dto.posterKey,
                title = dto.title,
                startDate = LocalDate.parse(dto.startDate, formatter),
                endDate = LocalDate.parse(dto.endDate, formatter),
                runningTime = Duration.ofMinutes(dto.runningTime.toLong()),
            )

        fun toUiModel(domain: MovieInfo): woowacourse.movie.uiModel.MovieInfoUIModel =
            MovieInfoUIModel(
                posterKey = domain.posterKey,
                title = domain.title,
                startDate = domain.startDate.format(formatter),
                endDate = domain.endDate.format(formatter),
                runningTime = domain.runningTime.toMinutes().toInt(),
            )
    }
}

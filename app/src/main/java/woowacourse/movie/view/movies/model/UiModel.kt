package woowacourse.movie.view.movies.model

import woowacourse.movie.domain.model.ad.Advertisement
import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.ext.toLocalDateFromDotFormat
import woowacourse.movie.view.movies.model.UiModel.MovieUiModel

sealed interface UiModel {
    data class MovieUiModel(
        val id: Int,
        val title: String,
        val imgName: String,
        val releaseStartDate: String,
        val releaseEndDate: String,
        val runningTime: Int,
    ) : UiModel {
        fun toDomain(): Movie {
            return Movie(
                id = id,
                title = title,
                posterResource = imgName,
                releaseDate =
                    ScreeningDate(
                        releaseStartDate.toLocalDateFromDotFormat(),
                        releaseEndDate.toLocalDateFromDotFormat(),
                    ),
                runningTime = runningTime,
            )
        }
    }

    data class AdvertiseUiModel(
        val imgResource: String,
    ) : UiModel
}

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        title = title,
        imgName = posterResource,
        releaseStartDate = StringFormatter.dotDateFormat(releaseDate.startDate),
        releaseEndDate = StringFormatter.dotDateFormat(releaseDate.endDate),
        runningTime = runningTime,
    )
}

fun Advertisement.toUiModel(): UiModel.AdvertiseUiModel {
    return UiModel.AdvertiseUiModel(
        imgResource = imgResource,
    )
}

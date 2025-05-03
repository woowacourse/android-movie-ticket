package woowacourse.movie.util.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_FORMAT
import woowacourse.movie.util.DateTimeUtil.toFormattedString
import woowacourse.movie.util.DateTimeUtil.toLocalDate

object MovieModelMapper {
    private val posterDrawableMapper = hashMapOf<Long, Poster>()

    fun toDomain(movieUiModel: MovieUiModel): Movie {
        posterDrawableMapper.putIfAbsent(movieUiModel.id, movieUiModel.poster)
        return Movie(
            movieUiModel.id,
            movieUiModel.title,
            movieUiModel.screeningStartDate.toLocalDate(MOVIE_DATE_DELIMITER),
            movieUiModel.screeningEndDate.toLocalDate(MOVIE_DATE_DELIMITER),
            movieUiModel.runningTime.toInt(),
        )
    }

    fun toUi(movie: Movie): MovieUiModel {
        val poster =
            posterDrawableMapper.getOrDefault(
                movie.id,
                defaultValue = Poster.Resource(R.drawable.prepare_poster),
            )
        return MovieUiModel(
            movie.id,
            poster,
            movie.title,
            movie.screeningStartDate.toFormattedString(MOVIE_DATE_FORMAT),
            movie.screeningEndDate.toFormattedString(MOVIE_DATE_FORMAT),
            movie.runningTime.toString(),
        )
    }
}

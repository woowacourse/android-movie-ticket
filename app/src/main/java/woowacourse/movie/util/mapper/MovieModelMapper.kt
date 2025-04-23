package woowacourse.movie.util.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_FORMAT

object MovieModelMapper {
    private val posterDrawableMapper = hashMapOf<Long, Poster>()

    fun toDomain(movieUiModel: MovieUiModel): Movie {
        posterDrawableMapper.putIfAbsent(movieUiModel.id, movieUiModel.poster)
        return Movie(
            movieUiModel.id,
            movieUiModel.title,
            DateTimeUtil.toLocalDate(movieUiModel.screeningStartDate, MOVIE_DATE_DELIMITER),
            DateTimeUtil.toLocalDate(movieUiModel.screeningEndDate, MOVIE_DATE_DELIMITER),
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
            DateTimeUtil.toFormattedString(movie.screeningStartDate, MOVIE_DATE_FORMAT),
            DateTimeUtil.toFormattedString(movie.screeningEndDate, MOVIE_DATE_FORMAT),
            movie.runningTime.toString(),
        )
    }
}

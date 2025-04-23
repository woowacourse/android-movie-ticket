package woowacourse.movie.util.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster

object MovieModelMapper {
    private val posterDrawableMapper = hashMapOf<Long, Poster>()

    fun toDomain(movieUiModel: MovieUiModel): Movie {
        posterDrawableMapper.putIfAbsent(movieUiModel.id, movieUiModel.poster)

        return Movie(
            movieUiModel.id,
            movieUiModel.title,
            movieUiModel.screeningStartDate,
            movieUiModel.screeningEndDate,
            movieUiModel.runningTime,
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
            movie.screeningStartDate,
            movie.screeningEndDate,
            movie.runningTime,
        )
    }
}

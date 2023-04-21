package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Movie
import woowacourse.movie.view.data.MovieListViewType
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.mapper.DateRangeMapper.toDomain
import woowacourse.movie.view.mapper.DateRangeMapper.toView
import woowacourse.movie.view.mapper.ImageMapper.toDomain
import woowacourse.movie.view.mapper.ImageMapper.toView

object MovieMapper : Mapper<Movie, MovieViewData> {
    override fun Movie.toView(): MovieViewData {
        return MovieViewData(
            poster.toView(),
            title,
            date.toView(),
            runningTime,
            description,
            MovieListViewType.MOVIE
        )
    }

    override fun MovieViewData.toDomain(): Movie {
        return Movie(
            poster.toDomain(),
            title,
            date.toDomain(),
            runningTime,
            description
        )
    }
}

package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Movie
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.mapper.DateRangeMapper.toDomain
import woowacourse.movie.view.mapper.DateRangeMapper.toView
import woowacourse.movie.view.mapper.ImageMapper.toDomain
import woowacourse.movie.view.mapper.ImageMapper.toView

object MovieMapper : Mapper<Movie, MovieView> {
    override fun Movie.toView(): MovieView {
        return MovieView(
            poster.toView(),
            title,
            date.toView(),
            runningTime,
            description
        )
    }

    override fun MovieView.toDomain(): Movie {
        return Movie(
            poster.toDomain(),
            title,
            date.toDomain(),
            runningTime,
            description
        )
    }
}

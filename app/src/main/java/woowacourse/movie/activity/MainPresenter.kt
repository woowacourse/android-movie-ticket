package woowacourse.movie.activity

import woowacourse.movie.dto.MovieDto
import woowacourse.movie.global.ServiceLocator

class MainPresenter : MainContract.Presenter {
    override fun movies(): List<MovieDto> {
        return ServiceLocator.movies.map { MovieDto.fromMovie(it) }
    }
}

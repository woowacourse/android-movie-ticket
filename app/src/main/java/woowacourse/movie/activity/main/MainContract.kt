package woowacourse.movie.activity.main

import woowacourse.movie.dto.MovieListDataDto

interface MainContract {
    interface View {
        fun initMovieDto(movies: List<MovieListDataDto>)
    }

    interface Presenter {
        fun initMovieDto()
    }
}

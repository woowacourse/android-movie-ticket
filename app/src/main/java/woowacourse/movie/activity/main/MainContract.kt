package woowacourse.movie.activity.main

import woowacourse.movie.dto.MovieDto

interface MainContract {
    interface View {
        fun initMovieDto(movies: List<MovieDto>)
    }

    interface Presenter {
        fun initMovieDto()
    }
}

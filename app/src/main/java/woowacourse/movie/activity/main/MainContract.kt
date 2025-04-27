package woowacourse.movie.activity.main

import woowacourse.movie.dto.MovieListData

interface MainContract {
    interface View {
        fun initMovieDto(movies: List<MovieListData>)
    }

    interface Presenter {
        fun initMovieDto()
    }
}

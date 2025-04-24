package woowacourse.movie.activity

import woowacourse.movie.dto.MovieDto

interface MainContract {
    interface View {
        fun navigate(movieDto: MovieDto)
    }

    interface Presenter {
        fun movies(): List<MovieDto>
    }
}

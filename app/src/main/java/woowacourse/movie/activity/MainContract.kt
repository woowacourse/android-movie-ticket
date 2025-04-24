package woowacourse.movie.activity

import woowacourse.movie.dto.MovieDto

interface MainContract {
    interface View

    interface Presenter {
        fun movies(): List<MovieDto>
    }
}

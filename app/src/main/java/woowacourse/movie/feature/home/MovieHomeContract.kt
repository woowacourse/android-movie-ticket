package woowacourse.movie.feature.home

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter

interface MovieHomeContract {
    interface View {
        fun setUpMovieContentList(movieContents: List<MovieContent>)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData()
    }
}

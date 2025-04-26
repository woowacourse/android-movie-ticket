package woowacourse.movie.movieList

import woowacourse.movie.R
import woowacourse.movie.dto.MovieInfo

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun onViewCreated() {
        val item =
            mutableListOf(
                MovieInfo(
                    R.drawable.harry_potter_poster,
                    "해리 포터와 마법사의 돌",
                    "2025.4.1",
                    "2025.4.25",
                    152,
                ),
            )

        if (item == null) {
            view.showError()
        } else {
            view.showMovies(item)
        }
    }

    override fun onButtonClicked(item: MovieInfo) {
        view.changeActivity(item)
    }

    override fun onError() {
        view.showError()
    }
}

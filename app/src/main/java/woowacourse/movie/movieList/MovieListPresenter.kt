package woowacourse.movie.movieList

import woowacourse.movie.R
import woowacourse.movie.dto.MovieInfo

class MovieListPresenter : MovieListContract.Presenter {
    override fun onViewCreated(view: MovieListContract.View) {
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

    override fun onButtonClicked(
        view: MovieListContract.View,
        item: MovieInfo,
    ) {
        view.changeActivity(item)
    }
}

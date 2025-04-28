package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel

interface MovieListContract {
    interface View {
        fun showMovies(items: List<MovieInfoUIModel>)

        fun showError()

        fun changeActivity(item: MovieInfoUIModel)
    }

    interface Presenter {
        fun onViewCreated()

        fun onButtonClicked(item: MovieInfoUIModel)

        fun onError()
    }
}

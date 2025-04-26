package woowacourse.movie.movieList

import woowacourse.movie.dto.MovieInfo

interface MovieListContract {
    interface View {
        fun showMovies(items: List<MovieInfo>)

        fun showError()

        fun changeActivity(item: MovieInfo)
    }

    interface Presenter {
        fun onViewCreated()

        fun onButtonClicked(item: MovieInfo)

        fun onError()
    }
}

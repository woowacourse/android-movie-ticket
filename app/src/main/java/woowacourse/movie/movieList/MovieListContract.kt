package woowacourse.movie.movieList

import woowacourse.movie.dto.MovieInfo

interface MovieListContract {
    interface View {
        fun showMovie(item: List<MovieInfo>)

        fun showError()

        fun changeActivity(item: MovieInfo)
    }

    interface Presenter {
        fun onViewCreated(view: View)

        fun onButtonClicked(
            view: View,
            item: MovieInfo,
        )
    }
}

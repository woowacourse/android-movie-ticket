package woowacourse.movie.view.movies

import android.os.Bundle

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)
    }

    interface Presenter {
        fun loadData(saveInstanceState: Bundle?)
    }
}

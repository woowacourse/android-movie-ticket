package woowacourse.movie.movie

import woowacourse.movie.DefaultDatas
import woowacourse.movie.R

class MovieListPresenter(private val view: MovieListContract.View) : MovieListContract.Presenter {
    override fun initMovies() {
        val movies = DefaultDatas.movies.map { MovieListItem.MovieItem(it) }
        val result = mutableListOf<MovieListItem>()

        movies.forEachIndexed { index, movieItem ->
            result.add(movieItem)
            if ((index + INDEX_OFFSET) % AD_INSERT_INTERVAL == 0) {
                result.add(MovieListItem.AdvertisementItem(R.drawable.advertisement))
            }
        }

        view.showMovies(result)
    }

    companion object {
        private const val AD_INSERT_INTERVAL: Int = 3
        private const val INDEX_OFFSET: Int = 1
    }
}

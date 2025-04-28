package woowacourse.movie.ui.movielist.presenter

import woowacourse.movie.domain.model.Advertisement
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListItem
import woowacourse.movie.ui.movielist.contract.MovieListContract

class MovieListPresenter(
    private val movieListView: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun getMovieList(): List<MovieListItem.MovieItem> =
        Movie.Companion.DUMMY_MOVIES.map {
            MovieListItem.MovieItem(
                it,
            )
        }

    override fun getAdvertisementList(): List<MovieListItem.AdItem> =
        Advertisement.Companion.DUMMY_ADS.map {
            MovieListItem.AdItem(
                it,
            )
        }

    override fun updateMovieList() {
        val movies = getMovieList()
        val ads = getAdvertisementList()
        movieListView.setMoveListItems(movieListItems(movies, ads))
    }

    private fun movieListItems(
        movies: List<MovieListItem.MovieItem>,
        ads: List<MovieListItem.AdItem>,
    ): List<MovieListItem> {
        val adsIterator = ads.iterator()
        val list: List<MovieListItem> =
            buildList {
                movies.forEachIndexed { index, movie ->
                    add(movie)
                    if (index % 3 == 2 && adsIterator.hasNext()) {
                        add(adsIterator.next())
                    }
                }
                while (adsIterator.hasNext()) {
                    add(adsIterator.next())
                }
            }
        return list
    }
}

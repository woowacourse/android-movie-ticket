package woowacourse.movie.view.movie

import woowacourse.movie.domain.model.Advertisement
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListItem
import woowacourse.movie.domain.model.MovieListItem.AdItem
import woowacourse.movie.domain.model.MovieListItem.MovieItem

class MovieListPresenter(
    val movieListView: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun getMovieList(): List<MovieItem> = Movie.DUMMY_MOVIES.map { MovieItem(it) }

    override fun getAdvertisementList(): List<AdItem> = Advertisement.DUMMY_ADS.map { AdItem(it) }

    override fun updateMovieList() {
        val movies = getMovieList()
        val ads = getAdvertisementList()
        movieListView.setMoveListItems(movieListItems(movies, ads))
    }

    private fun movieListItems(
        movies: List<MovieItem>,
        ads: List<AdItem>,
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

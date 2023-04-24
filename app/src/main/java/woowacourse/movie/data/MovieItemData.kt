package woowacourse.movie.data

import woowacourse.movie.presentation.main.MovieItem
import woowacourse.movie.presentation.mappers.toPresentation

object MovieItemData {
    private val movies = MovieData.movies.map { MovieItem.Movie(it.toPresentation()) }
    private val ads = AdData.ads

    fun getMovieItems(): List<MovieItem> {
        val movieItemModels = mutableListOf<MovieItem>()
        val mutableMovieModel = movies.toMutableList()
        val mutableAdModel = ads.toMutableList()

        repeat(10000) { position ->
            val target: MovieItem = if (position % 4 == 3) {
                mutableAdModel.removeFirst()
            } else {
                mutableMovieModel.removeFirst()
            }
            movieItemModels.add(target)
        }
        return movieItemModels.toList()
    }
}

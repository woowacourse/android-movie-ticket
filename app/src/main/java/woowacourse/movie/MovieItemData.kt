package woowacourse.movie

import woowacourse.movie.presentation.model.MovieItemModel
import woowacourse.movie.presentation.model.toPresentation

object MovieItemData {
    private val movieModels = MovieData.movies.map { it.toPresentation() }
    private val adModels = AdData.ads

    fun getMovieModelList(): List<MovieItemModel> {
        val movieItemModels = mutableListOf<MovieItemModel>()
        val mutableMovieModel = movieModels.toMutableList()
        val mutableAdModel = adModels.toMutableList()

        repeat(10000) { position ->
            val target: MovieItemModel = if (position % 4 == 3) {
                mutableAdModel.removeFirst()
            } else {
                mutableMovieModel.removeFirst()
            }
            movieItemModels.add(target)
        }
        return movieItemModels.toList()
    }
}

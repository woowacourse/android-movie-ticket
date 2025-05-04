package woowacourse.movie.domain.model

import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.presentation.movies.MoviesItem

class Advertise(private val movies: List<Movie>) {
    fun insertAdvertisement(): List<MoviesItem> {
        val result = mutableListOf<MoviesItem>()
        movies.forEachIndexed { index, movie ->
            result.add(MoviesItem.MovieItem(movie))
            if ((index + INDEX_INTERVAL) % ADS_INTERVAL == 0) {
                result.add(MoviesItem.AdvertisementItem(R.drawable.advertisement))
            }
        }
        return result
    }

    companion object {
        private const val INDEX_INTERVAL = 1
        private const val ADS_INTERVAL = 3
    }
}
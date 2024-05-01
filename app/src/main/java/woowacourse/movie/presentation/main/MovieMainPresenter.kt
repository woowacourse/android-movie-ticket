package woowacourse.movie.presentation.main

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.presentation.adapter.viewtype.MovieItemViewType
import woowacourse.movie.utils.SAMPLE_AD_URL

class MovieMainPresenter(
    private val movieChoiceContractView: MovieMainContract.View,
    private val movieRepository: MovieRepository = MovieRepositoryImpl(),
) :
    MovieMainContract.Presenter {
    override fun loadMovies() {
        movieChoiceContractView.onInitView(insertAds(movieRepository.findAllMovies()))
    }

    private fun insertAds(movieList: List<Movie>): List<MovieItemViewType> {
        val movieItemList = mutableListOf<MovieItemViewType>()
        movieList.forEachIndexed { index, movie ->
            movieItemList.add(MovieItemViewType.MovieView(movie = movie))
            if (index % AD_INTERVAL == AD_POSITION) {
                movieItemList.add(MovieItemViewType.AdView(SAMPLE_AD_URL))
            }
        }
        return movieItemList
    }

    companion object {
        const val AD_POSITION = 3
        const val AD_INTERVAL = 4
    }
}

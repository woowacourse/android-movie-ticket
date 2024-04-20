package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTicket

class MovieResultPresenter(private val resultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()

    override fun loadMovieTicket(
        id: Long,
        count: Int,
    ) {
        val movieData = movieRepository.getMovieById(id)
        resultContractView.displayMovieTicket(
            movieData?.let { movie ->
                MovieTicket(movie.title, movie.date, count)
            },
        )
    }
}

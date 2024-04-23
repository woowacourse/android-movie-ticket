package woowacourse.movie.result.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.contract.MovieResultContract

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

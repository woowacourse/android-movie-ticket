package woowacourse.movie.result.presenter

import woowacourse.movie.main.model.MovieRepository
import woowacourse.movie.result.model.MovieTicket
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

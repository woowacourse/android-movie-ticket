package woowacourse.movie.result.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.contract.MovieResultContract
import java.time.LocalDate
import java.time.LocalTime

class MovieResultPresenter(private val movieResultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()

    override fun loadMovieTicket(
        id: Long,
        date: String,
        time: String,
        count: Int,
    ) {
        val movieData = movieRepository.getMovieById(id)
        movieResultContractView.displayMovieTicket(
            movieData?.let { movie ->
                MovieTicket(movie.title, LocalDate.parse(date), LocalTime.parse(time), count)
            },
        )
    }
}

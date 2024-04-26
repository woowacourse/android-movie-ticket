package woowacourse.movie.result.presenter

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.MovieSeat
import woowacourse.movie.model.MovieSelectedSeats
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.contract.MovieResultContract
import java.time.LocalDate
import java.time.LocalTime

class MovieResultPresenter(private val movieResultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    override fun loadMovieTicket(
        id: Long,
        date: String,
        time: String,
        count: Int,
        seats: String,
    ) {
        val movieData = movieRepository.getMovieById(id)

        val movieSelectedSeats = MovieSelectedSeats(count)
        seats.split(", ").forEach { seat ->
            movieSelectedSeats.selectSeat(MovieSeat(seat[0] - 'A', seat.substring(1).toInt()))
        }

        movieResultContractView.displayMovieTicket(
            movieData?.let { movie ->
                MovieTicket(
                    movie.title,
                    LocalDate.parse(date),
                    LocalTime.parse(time),
                    count,
                    movieSelectedSeats,
                )
            },
        )
    }
}

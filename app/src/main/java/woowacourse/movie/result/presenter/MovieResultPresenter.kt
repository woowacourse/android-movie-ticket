package woowacourse.movie.result.presenter

import woowacourse.movie.data.MovieRepository.getMovieById
import woowacourse.movie.model.MovieSeat
import woowacourse.movie.model.MovieSelectedSeats
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.contract.MovieResultContract
import woowacourse.movie.util.Formatter.unFormatColumn
import woowacourse.movie.util.Formatter.unFormatRow
import java.time.LocalDate
import java.time.LocalTime

class MovieResultPresenter(private val movieResultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    override fun loadMovieTicket(
        id: Long,
        date: String,
        time: String,
        count: Int,
        seats: String,
    ) {
        val movieData = getMovieById(id)

        val movieSelectedSeats = MovieSelectedSeats(count)
        seats.split(", ").forEach { seat ->
            movieSelectedSeats.selectSeat(
                MovieSeat(
                    unFormatRow(seat),
                    unFormatColumn(seat),
                ),
            )
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

package woowacourse.movie.result.presenter

import woowacourse.movie.model.MovieSeat
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.contract.MovieResultContract
import java.time.LocalDate
import java.time.LocalTime

class MovieResultPresenter(private val movieResultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    override fun loadMovieTicket(
        title: String,
        date: String,
        time: String,
        count: Int,
        seats: String,
    ) {
        movieResultContractView.displayMovieTicket(
            MovieTicket(
                title,
                LocalDate.parse(date),
                LocalTime.parse(time),
                count,
                seats.split(", ").map { seat ->
                    MovieSeat(seat[0] - 'A', seat.substring(1).toInt())
                },
            ),
        )
    }
}

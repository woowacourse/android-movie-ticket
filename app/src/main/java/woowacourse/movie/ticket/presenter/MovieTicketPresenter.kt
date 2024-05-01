package woowacourse.movie.ticket.presenter

import woowacourse.movie.common.MovieDataSource
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.model.TicketDataResource
import woowacourse.movie.ticket.model.TicketDataResource.movieId
import woowacourse.movie.ticket.model.TicketDataResource.price
import woowacourse.movie.ticket.model.TicketDataResource.seats

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    override fun storeTicketCount(count: Count) {
        TicketDataResource.ticketCount = count
    }

    override fun storeMovieId(id: Long) {
        movieId = id
    }

    override fun setTicketInfo() {
        view.showTicketView(MovieDataSource.movieList[movieId.toInt()].title, price, seats.size)
    }

    override fun storeScreeningDate(date: String) {
        TicketDataResource.screeningDate = date
    }

    override fun storeScreeningTime(time: String) {
        TicketDataResource.screeningTime = time
    }

    override fun setScreeningDateInfo() {
        view.showScreeningDate(TicketDataResource.screeningDate)
    }

    override fun storePrice(price: Int) {
        TicketDataResource.price = price
    }

    override fun storeSeats(seats: List<Seat>) {
        TicketDataResource.seats = seats
    }

    override fun setSeatsInfo() {
        view.showSeats(seats)
    }

    override fun setScreeningTimeInfo() {
        view.showScreeningTime(TicketDataResource.screeningTime)
    }
}

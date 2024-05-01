package woowacourse.movie.ticket.contract

import woowacourse.movie.reservation.model.Count
import woowacourse.movie.seats.model.Seat

interface MovieTicketContract {
    interface View {
        val presenter: Presenter

        fun showScreeningDate(info: String)

        fun showScreeningTime(info: String)

        fun showTicketView(
            movieTitle: String,
            moviePrice: Int,
            ticketCount: Int,
        )

        fun showSeats(seats: List<Seat>)
    }

    interface Presenter {
        fun storeTicketCount(count: Count)

        fun setTicketInfo()

        fun storeScreeningDate(date: String)

        fun storeScreeningTime(time: String)

        fun setScreeningDateInfo()

        fun setScreeningTimeInfo()

        fun storeMovieId(id: Long)

        fun storePrice(price: Int)

        fun storeSeats(seats: List<Seat>)

        fun setSeatsInfo()
    }
}

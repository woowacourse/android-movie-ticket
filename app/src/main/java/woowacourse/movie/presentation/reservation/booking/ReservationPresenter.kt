package woowacourse.movie.presentation.reservation.booking

import android.content.Intent
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Payment
import woowacourse.movie.model.Ticket

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val dao: MovieDao,
) :
    ReservationContract.Presenter {
    private var ticket: Ticket = Ticket()
    private val payment: Payment = Payment()
    private lateinit var movie: Movie

    override fun fetchMovieDetail(movieId: Int) {
        movie = dao.find(movieId)
        movie.let {
            view.setUpView(
                movie.img,
                movie.title,
                movie.screenDateToString(),
                movie.runningTime,
                movie.description,
            )
        }
    }

    override fun subTicketCount() {
        ticket.subCount()
        view.updateTicketCount()
    }

    override fun addTicketCount() {
        ticket.addCount()
        view.updateTicketCount()
    }

    override fun clickReservationCompleteButton(intent: Intent) {
        intent.putExtra("movie", movie)
        intent.putExtra("ticket", ticket)
        intent.putExtra("payment", payment)
    }

    override fun ticketCount(): Int = ticket.count()

    override fun totalTicketPrice(): Int = payment.price(ticketCount())

    override fun restoreTicketCount(count: Int) {
        ticket.restoreCount(count)
    }
}

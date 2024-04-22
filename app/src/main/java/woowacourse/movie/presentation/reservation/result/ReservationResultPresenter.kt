package woowacourse.movie.presentation.reservation.result

import android.content.Intent
import woowacourse.movie.db.MovieDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Payment
import woowacourse.movie.model.Ticket

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val dao: MovieDao,
) :
    ReservationResultContract.Presenter {
    private lateinit var movie: Movie
    private lateinit var ticket: Ticket
    private lateinit var payment: Payment

    override fun fetchReservationDetail(intent: Intent) {
        movie = intent.getSerializableExtra("movie") as Movie
        ticket = intent.getSerializableExtra("ticket") as Ticket
        payment = intent.getSerializableExtra("payment") as Payment
        movie.let {
            view.setUpView(
                movie.title,
                movie.screenDateToString(),
                ticket.count(),
                payment.price(ticket.count()),
            )
        }
    }
}

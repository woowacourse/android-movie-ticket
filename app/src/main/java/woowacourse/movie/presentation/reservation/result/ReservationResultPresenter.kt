package woowacourse.movie.presentation.reservation.result

import android.content.Intent
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Payment
import woowacourse.movie.model.Ticket

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val dao: MovieDao,
) :
    ReservationResultContract.Presenter {

    override fun fetchReservationDetail(intent: Intent) {
        val movie = intent.getSerializableExtra("movie") as? Movie
        val ticket = intent.getSerializableExtra("ticket") as? Ticket
        val payment = intent.getSerializableExtra("payment") as? Payment
        movie?.let {
            view.setUpView(
                movie.title,
                movie.screenDateToString(),
                ticket!!.count(),
                payment!!.price(ticket.count()),
            )
        }
    }
}

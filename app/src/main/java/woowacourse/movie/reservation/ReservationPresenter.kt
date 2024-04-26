package woowacourse.movie.reservation

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.UiMovie

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private lateinit var ticket: Ticket

    override fun onViewCreated(intent: Intent) {
        val movie = getMovieDate(intent)
        movie?.let {
            ticket = Ticket(it)
            view.initializeMovieDetails(it)
            view.setupReservationCompletedButton(it)
        }
    }

    override fun getMovieDate(intent: Intent): UiMovie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_MOVIE, UiMovie::class.java)
        } else {
            intent.getSerializableExtra(EXTRA_MOVIE) as? UiMovie
        }
    }

    override fun onClicked(uiMovie: UiMovie) {
        view.moveToCompletedActivity(ticket)
    }

    override fun increaseQuantity() {
        ticket.increaseQuantity()
        view.setQuantityText("${ticket.quantity}")
    }

    override fun decreaseQuantity() {
        ticket.decreaseQuantity()
        view.setQuantityText("${ticket.quantity}")
    }

    companion object {
        const val EXTRA_MOVIE = "movie"
    }
}

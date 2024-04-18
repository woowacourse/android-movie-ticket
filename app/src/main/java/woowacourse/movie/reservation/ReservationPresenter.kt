package woowacourse.movie.reservation

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

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

    override fun getMovieDate(intent: Intent): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_MOVIE, Movie::class.java)
        } else {
            intent.getSerializableExtra(EXTRA_MOVIE) as? Movie
        }
    }

    override fun onClicked(movie: Movie) {
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

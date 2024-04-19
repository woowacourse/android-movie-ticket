package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.ReservationDetailPresenter
import woowacourse.movie.view.ReservationDetailContract

class ReservationDetailActivity : AppCompatActivity(), ReservationDetailContract {
    private val presenter = ReservationDetailPresenter(this)

    private val poster: ImageView by lazy { findViewById(R.id.image_view_reservation_detail_poster) }
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_detail_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_screening_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.text_view_reservation_running_time) }
    private val summary: TextView by lazy { findViewById(R.id.text_view_reservation_summary) }
    private val minusButton: Button by lazy { findViewById(R.id.button_reservation_detail_minus) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_detail_number_of_tickets) }
    private val plusButton: Button by lazy { findViewById(R.id.button_reservation_detail_plus) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_reservation_detail_finished) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra("movieId", DEFAULT_MOVIE_ID)

        with(presenter) {
            deliverMovie(movieId)
            deliverReservationHistory(movieId)
            detectIncreaseCount()
            detectDecreaseCount()
        }
    }

    override fun showMovieInformation(movie: Movie) {
        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text = movie.screeningDate
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    override fun changeNumberOfTickets(ticket: Ticket) {
        numberOfTickets.text = ticket.count.toString()
    }

    override fun initializePlusButton(increaseTicketCount: () -> Unit) {
        plusButton.setOnClickListener {
            increaseTicketCount()
        }
    }

    override fun initializeMinusButton(decreaseTicketCount: () -> Unit) {
        minusButton.setOnClickListener {
            decreaseTicketCount()
        }
    }

    override fun initializeReservationButton(movieId: Int) {
        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationFinishedActivity::class.java)
            intent.putExtra("movieId", movieId)
            intent.putExtra("ticket", presenter.ticket)
            startActivity(intent)
        }
    }

    override fun showResultToast() {
        Toast.makeText(this, getString(R.string.invalid_number_of_tickets), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val DEFAULT_MOVIE_ID = 0
    }
}

package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import domain.Ticket
import presenter.ReservationDetailPresenter
import view.ReservationDetailContract

class ReservationDetailActivity : AppCompatActivity(), ReservationDetailContract {
    private val presenter = ReservationDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra("movieId", 1)

        with(presenter) {
            deliverMovie(movieId)
            deliverReservationHistory(movieId)
            detectIncreaseCount()
            detectDecreaseCount()
        }
    }

    override fun showMovieInformation(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.image_view_reservation_detail_poster)
        val title = findViewById<TextView>(R.id.text_view_reservation_detail_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_screening_date)
        val runningTime = findViewById<TextView>(R.id.text_view_reservation_running_time)
        val summary = findViewById<TextView>(R.id.text_view_reservation_summary)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text = movie.screeningDate
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    override fun changeNumberOfTickets(ticket: Ticket) {
        val numberOfTickets =
            findViewById<TextView>(R.id.text_view_reservation_detail_number_of_tickets)

        numberOfTickets.text = ticket.count.toString()
    }

    override fun initializePlusButton(increaseTicketCount: () -> Unit) {
        val plusButton = findViewById<Button>(R.id.button_reservation_detail_plus)

        plusButton.setOnClickListener {
            increaseTicketCount()
        }
    }

    override fun initializeMinusButton(decreaseTicketCount: () -> Unit) {
        val minusButton = findViewById<Button>(R.id.button_reservation_detail_minus)

        minusButton.setOnClickListener {
            decreaseTicketCount()
        }
    }

    override fun initializeReservationButton(movieId: Int) {
        val reservationButton = findViewById<Button>(R.id.button_reservation_detail_finished)

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
}

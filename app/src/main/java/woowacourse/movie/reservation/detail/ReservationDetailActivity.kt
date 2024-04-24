package woowacourse.movie.reservation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.reservation.finished.ReservationFinishedActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationDetailActivity : AppCompatActivity(), ReservationDetailContract.View {
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_detail_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_screening_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.text_view_reservation_running_time) }
    private val summary: TextView by lazy { findViewById(R.id.text_view_reservation_summary) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_detail_number_of_tickets) }
    private val poster: ImageView by lazy { findViewById(R.id.image_view_reservation_detail_poster) }
    private val plusButton: Button by lazy { findViewById(R.id.button_reservation_detail_plus) }
    private val minusButton: Button by lazy { findViewById(R.id.button_reservation_detail_minus) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_reservation_detail_finished) }

    private lateinit var presenter: ReservationDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra("movieId", DEFAULT_MOVIE_ID)

        presenter = ReservationDetailPresenter(this, movieId)
        plusButton.setOnClickListener {
            presenter.increaseCount()
        }
        minusButton.setOnClickListener {
            presenter.decreaseCount()
        }
        reservationButton.setOnClickListener {
            presenter.deliverReservationInformation()
        }
    }

    override fun moveToReservationFinished(
        movieId: Int,
        ticketCount: Int,
    ) {
        startActivity(ReservationFinishedActivity.getIntent(this, movieId, ticketCount))
    }

    override fun showMovieInformation(movie: Movie) {
        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text = convertDateFormat(movie.screeningDate)
        runningTime.text = movie.runningTime
        summary.text = movie.summary
    }

    override fun updateCount(ticketCount: Int) {
        numberOfTickets.text = ticketCount.toString()
    }

    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.invalid_number_of_tickets), Toast.LENGTH_SHORT)
            .show()
    }

    private fun convertDateFormat(date: LocalDate): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        return date.format(dateFormat)
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val DEFAULT_MOVIE_ID = 0

        fun getIntent(
            context: Context,
            movieId: Int,
        ): Intent {
            return Intent(context, ReservationDetailActivity::class.java).also {
                it.putExtra(MOVIE_ID, movieId)
            }
        }
    }
}

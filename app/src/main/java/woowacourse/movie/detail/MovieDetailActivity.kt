package woowacourse.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.seat.SeatSelectActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_detail_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_screening_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.text_view_reservation_running_time) }
    private val summary: TextView by lazy { findViewById(R.id.text_view_reservation_summary) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_detail_number_of_tickets) }
    private val poster: ImageView by lazy { findViewById(R.id.image_view_reservation_detail_poster) }
    private val plusButton: Button by lazy { findViewById(R.id.button_reservation_detail_plus) }
    private val minusButton: Button by lazy { findViewById(R.id.button_reservation_detail_minus) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_reservation_detail_finished) }
    private val screeningDatesSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_detail_screening_date) }
    private val screeningTimeSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_detail_screening_time) }

    private lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)

        presenter = MovieDetailPresenter(this, movieId)
        presenter.loadMovie()
        presenter.loadScreeningDates()

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

    override fun showScreeningDates(screeningDates: List<LocalDate>) {
        screeningDatesSpinner.adapter =
            ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                screeningDates,
            )

        screeningDatesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val screeningDate = parent?.getItemAtPosition(position) as LocalDate
                    presenter.loadScreeningTimes(screeningDate)
                    presenter.updateScreeningDate(screeningDate.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showScreeningTimes(screeningTimes: List<String>) {
        screeningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                screeningTimes,
            )

        screeningTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val screeningTime = parent?.getItemAtPosition(position) as String
                    presenter.updateScreeningTime(screeningTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun moveToSeatSelect(
        movieTitle: String,
        ticketCount: Int,
        screeningDate: String,
        screeningTime: String,
    ) {
        startActivity(SeatSelectActivity.getIntent(this, movieTitle, ticketCount, screeningDate, screeningTime))
    }

    override fun showMovieInformation(movie: Movie) {
        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text = convertDateFormat(movie.firstScreeningDate, movie.lastScreeningDate)
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

    private fun convertDateFormat(
        firstDate: LocalDate,
        secondDate: LocalDate,
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        return "${firstDate.format(dateFormat)} ~ ${secondDate.format(dateFormat)}"
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val DEFAULT_MOVIE_ID = 0

        fun getIntent(
            context: Context,
            movieId: Int,
        ): Intent {
            return Intent(context, MovieDetailActivity::class.java).also {
                it.putExtra(MOVIE_ID, movieId)
            }
        }
    }
}

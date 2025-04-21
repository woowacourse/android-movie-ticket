package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.SpinnerAdapter
import woowacourse.movie.domain.Movie
import woowacourse.movie.ui.MovieUiModel
import woowacourse.movie.domain.TicketManager

class BookingActivity : AppCompatActivity() {
    private lateinit var movieUiModel: MovieUiModel
    private lateinit var ticketManager: TicketManager
    private lateinit var movieTime: Spinner
    private lateinit var movieDate: Spinner
    private lateinit var ticketCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        movieDate = findViewById(R.id.movie_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)

        val movie: Movie = intent.getParcelableExtra(KEY_MOVIE_INFO)
            ?: run {
                Toast.makeText(this, R.string.no_movie_data_error_message, Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        movieUiModel = MovieUiModel.fromDomain(movie)
        ticketManager = TicketManager(movie)

        setupPage()
        setupDateChangeListener()
        countButtonHandler()
        confirmButtonHandler()
        if (savedInstanceState != null) {
            repairInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_TICKET_COUNT, ticketCount.text.toString())
        outState.putInt(KEY_MOVIE_DATE_POSITION, movieDate.selectedItemPosition)
        outState.putInt(KEY_MOVIE_TIME_POSITION, movieTime.selectedItemPosition)
    }

    private fun repairInstanceState(state: Bundle) {
        movieDate.setSelection(state.getInt(KEY_MOVIE_DATE_POSITION))
        movieDate.post {
            movieTime.post {
                movieTime.setSelection(state.getInt(KEY_MOVIE_TIME_POSITION))
            }
        }
        ticketCount.text = state.getString(KEY_TICKET_COUNT)
    }

    private fun setupPage() {
        val title: TextView = findViewById(R.id.title)
        val runningTime:TextView = findViewById(R.id.running_time)
        val poster:ImageView = findViewById(R.id.movie_poster)

        poster.setImageResource(movieUiModel.poster)
        title.text = movieUiModel.title
        findViewById<TextView>(R.id.start_date).text = movieUiModel.startDate
        findViewById<TextView>(R.id.end_date).text = movieUiModel.endDate
        runningTime.text = movieUiModel.runningTime

        val dates = ticketManager.getDates()
        val times = ticketManager.getTimes(dates[0])
        SpinnerAdapter.bind(this, movieDate, dates)
        SpinnerAdapter.bind(this, movieTime, times)
    }

    private fun setupDateChangeListener() {
        movieDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    ticketManager.setDatePosition(position)
                    val selectedDate = ticketManager.getDates()[position]
                    val times = ticketManager.getTimes(selectedDate)
                    SpinnerAdapter.bind(this@BookingActivity, movieTime, times)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun confirmButtonHandler() {
        val confirmButton : Button = findViewById(R.id.confirm_button)

        confirmButton.setOnClickListener {
            if (ticketManager.getTicketCount() == 0) return@setOnClickListener
            askToConfirmBooking()
        }
    }

    private fun countButtonHandler() {
        val minusButton : Button = findViewById(R.id.minus_button)
        val plusButton : Button = findViewById(R.id.plus_button)

        minusButton.setOnClickListener {
            ticketManager.decrementTicketCount()
            ticketCount.text = "${ticketManager.getTicketCount()}"
        }

        plusButton.setOnClickListener {
            ticketManager.incrementTicketCount()
            ticketCount.text = "${ticketManager.getTicketCount()}"
        }
    }

    private fun askToConfirmBooking() {
        val ticket = ticketManager.createTicket()
        ConfirmDialog.show(this, ticket) {
            val intent =
                Intent(this, BookingResultActivity::class.java).apply {
                    putExtra(KEY_TICKET, ticket)
                }
            startActivity(intent)
        }
    }

    companion object {
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        const val KEY_TICKET = "TICKET"
    }
}

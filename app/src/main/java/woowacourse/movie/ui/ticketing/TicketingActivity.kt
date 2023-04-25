package woowacourse.movie.ui.ticketing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.extensions.exitForUnNormalCase
import woowacourse.movie.extensions.getParcelableCompat
import woowacourse.movie.extensions.showToast
import woowacourse.movie.model.MovieUI
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.mapper.toMovie
import woowacourse.movie.model.mapper.toReservationUI
import woowacourse.movie.ui.movielist.MovieListActivity
import woowacourse.movie.ui.seatselection.SeatSelectionActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketingActivity : AppCompatActivity(), OnClickListener {
    private lateinit var movie: MovieUI
    private var movieTicket: TicketCountUI = TicketCountUI()

    private val movieDates: List<LocalDate> by lazy {
        movie.toMovie().getRunningDates()
    }
    private val movieTimes = mutableListOf<LocalTime>()
    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this@TicketingActivity, android.R.layout.simple_spinner_item, mutableListOf())
    }

    private var selectedDateIdx: Int = DEFAULT_DATE_SELECTED_IDX
    private var selectedTimeIdx: Int = DEFAULT_TIME_SELECTED_IDX

    private val textViewTicketCount: TextView by lazy {
        findViewById(R.id.tv_ticket_count)
    }
    private val spinnerMovieDate: Spinner by lazy {
        findViewById(R.id.spinner_movie_date)
    }
    private val spinnerMovieTime: Spinner by lazy {
        findViewById(R.id.spinner_movie_time)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movie = intent.getParcelableCompat(MovieListActivity.MOVIE_KEY)
            ?: return exitForUnNormalCase(MESSAGE_EMPTY_MOVIE)

        initMovieInfo()
        initButtonOnClickListener()
        initMovieDateSpinnerAdapter()
        initMovieDateSpinnerItemClick()
        initMovieTimeSpinner()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreState(savedInstanceState)
        initMovieTimes()
        setTicketCount(movieTicket)
        spinnerMovieDate.setSelection(selectedDateIdx)
        spinnerMovieTime.setSelection(selectedTimeIdx)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        savedInstanceState.run {
            movieTicket = getParcelableCompat(TICKET_STATE_KEY) ?: TicketCountUI()
            selectedDateIdx = getInt(MOVIE_DATE_INDEX_STATE_KEY)
            selectedTimeIdx = getInt(MOVIE_TIME_INDEX_STATE_KEY)
        }
    }

    private fun initMovieInfo() {
        with(movie) {
            thumbnail?.let { findViewById<ImageView>(R.id.iv_poster).setImageResource(it) }
            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_date).text = getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            findViewById<TextView>(R.id.tv_running_time).text =
                getString(R.string.movie_running_time, runningTime)
            findViewById<TextView>(R.id.tv_introduce).text = introduce
        }
    }

    private fun setTicketCount(ticket: TicketCountUI) {
        movieTicket = ticket
        textViewTicketCount.text = ticket.count.toString()
    }

    private fun initButtonOnClickListener() {
        val btnMinus: Button = findViewById(R.id.btn_minus)
        val btnPlus: Button = findViewById(R.id.btn_plus)
        val btnTicketing: Button = findViewById(R.id.btn_ticketing)

        btnMinus.setOnClickListener(this@TicketingActivity)
        btnPlus.setOnClickListener(this@TicketingActivity)
        btnTicketing.setOnClickListener(this@TicketingActivity)
    }

    private fun initMovieDateSpinnerAdapter() {
        spinnerMovieDate.adapter = ArrayAdapter(
            this@TicketingActivity,
            android.R.layout.simple_spinner_item,
            movieDates.map { it.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN)) }
        )
    }

    private fun initMovieDateSpinnerItemClick() {
        spinnerMovieDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedDateIdx = pos
                initMovieTimes()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initMovieTimeSpinner() {
        spinnerMovieTime.adapter = movieTimeAdapter
        initMovieTimes()
        initMovieTimeSpinnerItemClick()
    }

    private fun initMovieTimes() {
        with(movie) {
            movieTimes.clear()
            movieTimes.addAll(toMovie().getRunningTimes(movieDates[selectedDateIdx]))
            movieTimeAdapter.clear()
            movieTimeAdapter.addAll(
                movieTimes.map { it.format(DateTimeFormatter.ofPattern(MOVIE_TIME_PATTERN)) }
            )
        }
    }

    private fun initMovieTimeSpinnerItemClick() {
        spinnerMovieTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedTimeIdx = pos
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                setTicketCount(movieTicket.decreaseTicketCount())
            }
            R.id.btn_plus -> {
                setTicketCount(movieTicket.increaseTicketCount())
            }
            R.id.btn_ticketing -> {
                onClickTicketing()
            }
        }
    }

    private fun onClickTicketing() {
        if (selectedTimeIdx == -1) {
            showToast(getString(R.string.select_date_and_time))
            return
        }
        if (movieTicket.count == 0) {
            showToast(getString(R.string.select_ticket_count))
            return
        }
        reserveMovie()?.let {
            val intent =
                SeatSelectionActivity.getIntent(this@TicketingActivity, it, movieTicket)
            startActivity(intent)
        }
    }

    private fun reserveMovie(): ReservationUI? {
        val reservationDateTime = LocalDateTime.of(
            movieDates[selectedDateIdx],
            movieTimes[selectedTimeIdx]
        )
        return movie.toMovie().reserveMovie(reservationDateTime)
            ?.toReservationUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKET_STATE_KEY, movieTicket)
        outState.putInt(MOVIE_DATE_INDEX_STATE_KEY, selectedDateIdx)
        outState.putInt(MOVIE_TIME_INDEX_STATE_KEY, selectedTimeIdx)
    }

    companion object {
        private const val MESSAGE_EMPTY_MOVIE = "영화 정보가 없습니다"
        private const val DEFAULT_DATE_SELECTED_IDX = 0
        private const val DEFAULT_TIME_SELECTED_IDX = -1

        private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"
        private const val MOVIE_TIME_PATTERN = "HH:mm"

        internal const val TICKET_COUNT_KEY = "ticketCount"
        internal const val RESERVATION_KEY = "reservation"

        private const val TICKET_STATE_KEY = "ticket"
        private const val MOVIE_DATE_INDEX_STATE_KEY = "movieDate"
        private const val MOVIE_TIME_INDEX_STATE_KEY = "movieTime"

        internal fun getIntent(context: Context, movie: MovieUI): Intent {
            val intent = Intent(context, TicketingActivity::class.java)
            intent.putExtra(MovieListActivity.MOVIE_KEY, movie)
            return intent
        }
    }
}

package woowacourse.movie.view.activities

import android.content.Intent
import android.os.Build
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
import woowacourse.movie.data.Movie
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.getSerializable
import woowacourse.movie.showToast

class TicketingActivity : AppCompatActivity(), OnClickListener {
    private var movieTicket: Ticket = Ticket()

    private val movie: Movie by lazy {
        intent.getSerializable(MovieListActivity.MOVIE_KEY)!!
    }

    private val movieDates: List<MovieDate> by lazy {
        intent.getSerializable<Movie>(MovieListActivity.MOVIE_KEY)
            ?.run { MovieDate.releaseDates(startDate, endDate) }
            ?: emptyList()
    }
    private val movieTimes = mutableListOf<MovieTime>()

    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this@TicketingActivity, android.R.layout.simple_spinner_item, mutableListOf())
    }
    private var selectedDate: MovieDate? = null
    private var selectedTime: MovieTime? = null

    private val tvTicketCount: TextView by lazy {
        findViewById(R.id.tv_ticket_count)
    }
    private val btnMinus: Button by lazy {
        findViewById(R.id.btn_minus)
    }
    private val btnPlus: Button by lazy {
        findViewById(R.id.btn_plus)
    }
    private val btnTicketing: Button by lazy {
        findViewById(R.id.btn_ticketing)
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

        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvDate: TextView = findViewById(R.id.tv_date)
        val tvRunningTime: TextView = findViewById(R.id.tv_running_time)
        val tvIntroduce: TextView = findViewById(R.id.tv_introduce)

        val ivPoster: ImageView = findViewById(R.id.iv_poster)

        movie.run {
            ivPoster.setImageResource(thumbnail)
            tvTitle.text = title
            tvDate.text = getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            tvRunningTime.text = getString(R.string.movie_running_time, runningTime)
            tvIntroduce.text = introduce
        }

        spinnerMovieDate.adapter = ArrayAdapter(
            this@TicketingActivity,
            android.R.layout.simple_spinner_item,
            movieDates.map {
                getString(
                    R.string.book_date,
                    it.year,
                    it.month,
                    it.day
                )
            }
        )
        spinnerMovieTime.adapter = movieTimeAdapter
        movieTimeAdapter.setNotifyOnChange(true)
        spinnerMovieTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedTime = this@TicketingActivity.movieTimes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerMovieDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedDate = movieDates[pos]
                this@TicketingActivity.movieTimes.clear()
                selectedDate?.run {
                    this@TicketingActivity.movieTimes.addAll(
                        MovieTime.runningTimes(
                            isWeekend(),
                            isToday()
                        )
                    )
                }
                movieTimeAdapter.clear()
                movieTimeAdapter.addAll(
                    this@TicketingActivity.movieTimes.map {
                        getString(
                            R.string.book_time,
                            it.hour,
                            it.min
                        )
                    }
                )
                selectedTime = this@TicketingActivity.movieTimes.firstOrNull()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnMinus.setOnClickListener(this@TicketingActivity)
        btnPlus.setOnClickListener(this@TicketingActivity)
        btnTicketing.setOnClickListener(this@TicketingActivity)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreState(savedInstanceState)
        tvTicketCount.text = movieTicket.count.toString()
        val storedDateIndex = movieDates.indexOfFirst { it == selectedDate }
        spinnerMovieDate.setSelection(storedDateIndex)
        val storedTimeIndex = this.movieTimes.indexOfFirst { it == selectedTime }
        spinnerMovieTime.setSelection(storedTimeIndex)
    }

    @Suppress("DEPRECATION")
    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                selectedDate = getSerializable(SELECTED_DATE_STATE_KEY, MovieDate::class.java)
                selectedTime = getSerializable(SELECTED_TIME_STATE_KEY, MovieTime::class.java)
                movieTicket = getSerializable(TICKET_COUNT_STATE_KEY, Ticket::class.java)!!
            } else {
                selectedDate = getSerializable(SELECTED_DATE_STATE_KEY) as MovieDate
                selectedTime = getSerializable(SELECTED_TIME_STATE_KEY) as MovieTime
                movieTicket = getSerializable(TICKET_COUNT_STATE_KEY) as Ticket
            }
        }
        selectedDate?.run { this@TicketingActivity.movieTimes.addAll(MovieTime.runningTimes(isWeekend(), isToday())) }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                movieTicket = --movieTicket
                tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_plus -> {
                movieTicket = ++movieTicket
                tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_ticketing -> {
                if (selectedDate == null || selectedTime == null) {
                    showToast(getString(R.string.select_date_and_time))
                    return
                }

                val intent = Intent(this@TicketingActivity, TicketingResultActivity::class.java)
                intent.putExtra(MovieListActivity.MOVIE_KEY, movie)
                intent.putExtra(TICKET_KEY, movieTicket)
                intent.putExtra(MOVIE_DATE_KEY, selectedDate)
                intent.putExtra(MOVIE_TIME_KEY, selectedTime)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this@TicketingActivity, MovieListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(TICKET_COUNT_STATE_KEY, movieTicket)
        outState.putSerializable(SELECTED_DATE_STATE_KEY, selectedDate)
        outState.putSerializable(SELECTED_TIME_STATE_KEY, selectedTime)
    }

    companion object {
        internal const val TICKET_KEY = "ticketCount"
        internal const val MOVIE_DATE_KEY = "movieDate"
        internal const val MOVIE_TIME_KEY = "movieTime"

        internal const val SELECTED_DATE_STATE_KEY = "selectedDate"
        internal const val SELECTED_TIME_STATE_KEY = "selectedTime"
        internal const val TICKET_COUNT_STATE_KEY = "ticketCountState"
    }
}

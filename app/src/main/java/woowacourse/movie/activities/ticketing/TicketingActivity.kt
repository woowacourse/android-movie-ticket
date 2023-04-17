package woowacourse.movie.activities.ticketing

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
import com.woowacourse.movie.domain.RunningDate
import com.woowacourse.movie.domain.RunningTime
import woowacourse.movie.R
import woowacourse.movie.activities.movielist.MovieListActivity
import woowacourse.movie.activities.ticketingresult.TicketingResultActivity
import woowacourse.movie.customGetParcelable
import woowacourse.movie.getParcelable
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.mapper.toDomain
import woowacourse.movie.model.mapper.toPresentation
import woowacourse.movie.showToast
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketingActivity : AppCompatActivity(), OnClickListener {
    private val runningDate = RunningDate()
    private val runningTime = RunningTime()
    private var movieTicket: Ticket = Ticket()

    private val movie: Movie? by lazy {
        intent.getParcelable(MovieListActivity.MOVIE_KEY)
    }

    private val movieDates: List<LocalDate> by lazy {
        intent.getParcelable<Movie>(MovieListActivity.MOVIE_KEY)
            ?.run {
                runningDate.getRunningDates(startDate, endDate)
            } ?: emptyList()
    }
    private val movieTimes = mutableListOf<LocalTime>()

    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this@TicketingActivity, android.R.layout.simple_spinner_item, mutableListOf())
    }
    private var reservation: Reservation? = null
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null

    private val tvTicketCount: TextView by lazy {
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

        setMovieInfo()
        setButtonOnClickListener()
        setMovieDateSpinnerAdapter()
        setMovieTimeSpinnerAdapter()
        setMovieDateSpinnerItemClick()
        setMovieTimeSpinnerItemClick()
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

    private fun restoreState(savedInstanceState: Bundle) {
        savedInstanceState.run {
            reservation = customGetParcelable<Reservation>(RESERVATION_KEY)?.apply {
                selectedDate = dateTime.toLocalDate()?.apply { initMovieTimes(this) }
                selectedTime = dateTime.toLocalTime()
                movieTicket = ticket
            }
        }
    }

    private fun setMovieInfo() {
        movie?.run {
            findViewById<ImageView>(R.id.iv_poster).setImageResource(thumbnail)
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

    private fun setButtonOnClickListener() {
        val btnMinus: Button = findViewById(R.id.btn_minus)
        val btnPlus: Button = findViewById(R.id.btn_plus)
        val btnTicketing: Button = findViewById(R.id.btn_ticketing)

        btnMinus.setOnClickListener(this@TicketingActivity)
        btnPlus.setOnClickListener(this@TicketingActivity)
        btnTicketing.setOnClickListener(this@TicketingActivity)
    }

    private fun setMovieDateSpinnerAdapter() {
        spinnerMovieDate.adapter = ArrayAdapter(
            this@TicketingActivity,
            android.R.layout.simple_spinner_item,
            movieDates.map { it.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN)) }
        )
    }

    private fun setMovieDateSpinnerItemClick() {
        spinnerMovieDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedDate = movieDates[pos].apply {
                    initMovieTimes(this)
                }
                selectedTime = movieTimes.firstOrNull()
                spinnerMovieTime.setSelection(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setMovieTimeSpinnerAdapter() {
        spinnerMovieTime.adapter = movieTimeAdapter
    }

    private fun setMovieTimeSpinnerItemClick() {
        spinnerMovieTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long
            ) {
                selectedTime = movieTimes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initMovieTimes(date: LocalDate) {
        movieTimes.clear()
        movieTimes.addAll(runningTime.runningTimes(date))
        movieTimeAdapter.clear()
        movieTimeAdapter.addAll(
            movieTimes.map { it.format(DateTimeFormatter.ofPattern(MOVIE_TIME_PATTERN)) }
        )
        movieTimeAdapter.notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                var movieTicket = movieTicket.toDomain()
                this.movieTicket = (--movieTicket).toPresentation()
                tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_plus -> {
                var movieTicket = movieTicket.toDomain()
                this.movieTicket = (++movieTicket).toPresentation()
                tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_ticketing -> {
                if (selectedDate == null || selectedTime == null) {
                    showToast(getString(R.string.select_date_and_time))
                    return
                }

                val intent = Intent(this@TicketingActivity, TicketingResultActivity::class.java)
                reservation = Reservation(
                    movie!!,
                    LocalDateTime.of(selectedDate!!, selectedTime!!),
                    movieTicket
                )
                intent.putExtra(RESERVATION_KEY, reservation)
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
        reservation = Reservation(
            movie!!,
            LocalDateTime.of(selectedDate, selectedTime),
            movieTicket
        )
        outState.putParcelable(RESERVATION_KEY, reservation)
    }

    companion object {
        private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"
        private const val MOVIE_TIME_PATTERN = "HH:mm"

        internal const val RESERVATION_KEY = "reservation"
    }
}

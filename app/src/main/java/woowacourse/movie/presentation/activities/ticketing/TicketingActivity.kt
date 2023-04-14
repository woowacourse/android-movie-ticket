package woowacourse.movie.presentation.activities.ticketing

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityTicketingBinding
import woowacourse.movie.domain.model.movie.MovieDate
import woowacourse.movie.domain.model.movie.MovieTime
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.activities.ticketingresult.TicketingResultActivity
import woowacourse.movie.presentation.extensions.getParcelableExtraCompat
import woowacourse.movie.presentation.extensions.showToast
import woowacourse.movie.presentation.model.Movie

class TicketingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTicketingBinding

    private var movieTicket: Ticket = Ticket()

    private val movieDates: List<MovieDate> by lazy {
        intent.getParcelableExtraCompat<Movie>(MOVIE_KEY)?.run {
            MovieDate.releaseDates(from = startDate, to = endDate)
        } ?: emptyList()
    }
    private val movieTimes = mutableListOf<MovieTime>()

    private val movieDateAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            movieDates.map { getString(R.string.book_date, it.year, it.month, it.day) }
        )
    }
    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf())
    }
    private var selectedDate: MovieDate? = null
    private var selectedTime: MovieTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreState(savedInstanceState)

        with(binding) {
            showMovieIntroduce()
            setSpinnerConfig()
            setClickListener()
        }
    }

    private fun ActivityTicketingBinding.setClickListener() {
        btnMinus.setOnClickListener(this@TicketingActivity)
        btnPlus.setOnClickListener(this@TicketingActivity)
        btnTicketing.setOnClickListener(this@TicketingActivity)
    }

    private fun ActivityTicketingBinding.setSpinnerConfig() {
        setSpinnerAdapter()
        setSpinnerListener()
    }

    private fun ActivityTicketingBinding.setSpinnerAdapter() {
        spinnerMovieDate.adapter = movieDateAdapter
        spinnerMovieTime.adapter = movieTimeAdapter
        movieDateAdapter.setNotifyOnChange(true)
        movieTimeAdapter.setNotifyOnChange(true)
    }

    private fun ActivityTicketingBinding.setSpinnerListener() {
        spinnerMovieTime.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long,
            ) {
                selectedTime = movieTimes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerMovieDate.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long,
            ) {
                selectedDate = movieDates[pos]
                movieTimes.clear()
                selectedDate?.run {
                    movieTimes.addAll(
                        MovieTime.runningTimes(
                            isWeekend(),
                            isToday()
                        )
                    )
                }
                movieTimeAdapter.clear()
                movieTimeAdapter.addAll(
                    movieTimes.map { getString(R.string.book_time, it.hour, it.min) }
                )
                selectedTime = movieTimes.firstOrNull()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun ActivityTicketingBinding.showMovieIntroduce() {
        intent.getParcelableExtraCompat<Movie>(MOVIE_KEY)?.run {
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
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            selectedDate = getParcelableExtraCompat(SELECTED_DATE_STATE_KEY)
            selectedTime = getParcelableExtraCompat(SELECTED_TIME_STATE_KEY)
            movieTicket = getParcelableExtraCompat(TICKET_COUNT_STATE_KEY)!!
        }
        selectedDate?.run { movieTimes.addAll(MovieTime.runningTimes(isWeekend(), isToday())) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvTicketCount.text = movieTicket.count.toString()
        val storedDateIndex = movieDates.indexOfFirst { it == selectedDate }
        binding.spinnerMovieDate.setSelection(storedDateIndex)
        val storedTimeIndex = movieTimes.indexOfFirst { it == selectedTime }
        binding.spinnerMovieTime.setSelection(storedTimeIndex)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                movieTicket = --movieTicket
                binding.tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_plus -> {
                movieTicket = ++movieTicket
                binding.tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_ticketing -> {
                if (selectedDate == null || selectedTime == null) {
                    showToast(getString(R.string.select_date_and_time))
                    return
                }
                startActivity(
                    Intent(this, TicketingResultActivity::class.java)
                        .putExtra(MOVIE_KEY, intent.getParcelableExtraCompat<Movie>(MOVIE_KEY))
                        .putExtra(TICKET_KEY, movieTicket)
                        .putExtra(MOVIE_DATE_KEY, selectedDate)
                        .putExtra(MOVIE_TIME_KEY, selectedTime)
                )
                finish()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKET_COUNT_STATE_KEY, movieTicket)
        outState.putParcelable(SELECTED_DATE_STATE_KEY, selectedDate)
        outState.putParcelable(SELECTED_TIME_STATE_KEY, selectedTime)
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

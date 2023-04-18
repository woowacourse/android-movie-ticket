package woowacourse.movie.presentation.activities.ticketing

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityTicketingBinding
import woowacourse.movie.domain.model.movie.DomainMovieDate
import woowacourse.movie.domain.model.movie.DomainMovieTime
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.extensions.getParcelableCompat
import woowacourse.movie.presentation.extensions.showBackButton
import woowacourse.movie.presentation.extensions.showToast
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toPresentation
import woowacourse.movie.presentation.model.Movie
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.Ticket

class TicketingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTicketingBinding

    private var movieTicket: Ticket = Ticket()
    private var selectedDate: MovieDate? = null
    private var selectedTime: MovieTime? = null

    private val movieDates: List<MovieDate> by lazy {
        intent.getParcelableCompat<Movie>(MOVIE_KEY)?.run {
            DomainMovieDate.releaseDates(from = startDate, to = endDate).map { it.toPresentation() }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreState(savedInstanceState)

        showBackButton()
        showMovieIntroduce()
        setSpinnerConfig()
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            btnMinus.setOnClickListener(this@TicketingActivity)
            btnPlus.setOnClickListener(this@TicketingActivity)
            btnTicketing.setOnClickListener(this@TicketingActivity)
        }
    }

    private fun setSpinnerConfig() {
        setSpinnerAdapter()
        setSpinnerListener()
    }

    private fun setSpinnerAdapter() {
        with(binding) {
            spinnerMovieDate.adapter = movieDateAdapter.also { it.setNotifyOnChange(true) }
            spinnerMovieTime.adapter = movieTimeAdapter.also { it.setNotifyOnChange(true) }
        }
    }

    private fun setSpinnerListener() {
        setMovieTimeSpinnerListener()
        setMovieDateSpinnerListener()
    }

    private fun setMovieTimeSpinnerListener() {
        binding.spinnerMovieTime.onItemSelectedListener = object : OnItemSelectedListener {
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
    }

    private fun setMovieDateSpinnerListener() {
        binding.spinnerMovieDate.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long,
            ) {
                selectedDate = movieDates[pos]
                selectedDate?.toDomain()?.run {
                    updateMovieTimes(
                        DomainMovieTime.runningTimes(isWeekend(), isToday())
                            .map { it.toPresentation() }
                    )
                }
                updateMovieTimeAdapterItems(
                    movieTimes.map {
                        getString(R.string.book_time, it.hour, it.min)
                    }
                )
                selectedTime = movieTimes.firstOrNull()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateMovieTimes(newMovieTimes: List<MovieTime>) {
        movieTimes.clear()
        movieTimes.addAll(newMovieTimes)
    }

    private fun updateMovieTimeAdapterItems(movieTime: List<String>) {
        movieTimeAdapter.clear()
        movieTimeAdapter.addAll(movieTime)
    }

    private fun showMovieIntroduce() {
        with(binding) {
            intent.getParcelableCompat<Movie>(MOVIE_KEY)?.run {
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
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            selectedDate = getParcelableCompat(SELECTED_DATE_STATE_KEY)
            selectedTime = getParcelableCompat(SELECTED_TIME_STATE_KEY)
            movieTicket = getParcelableCompat(TICKET_COUNT_STATE_KEY)!!
        }
        selectedDate?.toDomain()?.run {
            updateMovieTimes(
                DomainMovieTime.runningTimes(isWeekend(), isToday()).map { it.toPresentation() }
            )
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvTicketCount.text = movieTicket.count.toString()
        binding.spinnerMovieDate.setSelection(movieDates.indexOf(selectedDate))
        binding.spinnerMovieTime.setSelection(movieTimes.indexOf(selectedTime))
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                movieTicket = (movieTicket.toDomain() - 1).toPresentation()
                binding.tvTicketCount.text = movieTicket.count.toString()
            }

            R.id.btn_plus -> {
                movieTicket = (movieTicket.toDomain() + 1).toPresentation()
                binding.tvTicketCount.text = movieTicket.count.toString()
            }

            R.id.btn_ticketing -> {
                if (selectedDate == null || selectedTime == null) {
                    showToast(getString(R.string.select_date_and_time))
                    return
                }
                startSeatPickerActivity()
            }
        }
    }

    private fun startSeatPickerActivity() {
        startActivity(
            Intent(this, SeatPickerActivity::class.java)
                .putExtra(MOVIE_KEY, intent.getParcelableCompat<Movie>(MOVIE_KEY))
                .putExtra(TICKET_KEY, movieTicket)
                .putExtra(MOVIE_DATE_KEY, selectedDate)
                .putExtra(MOVIE_TIME_KEY, selectedTime)
        )
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKET_COUNT_STATE_KEY, movieTicket)
        outState.putParcelable(SELECTED_DATE_STATE_KEY, selectedDate)
        outState.putParcelable(SELECTED_TIME_STATE_KEY, selectedTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
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

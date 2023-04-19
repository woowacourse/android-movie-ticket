package woowacourse.movie.presentation.activities.ticketing

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.DomainMovieDate
import woowacourse.movie.domain.model.movie.DomainMovieTime
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.extensions.getParcelableCompat
import woowacourse.movie.presentation.extensions.showBackButton
import woowacourse.movie.presentation.extensions.showToast
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toPresentation
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.Ticket
import woowacourse.movie.presentation.model.movieitem.Movie

class TicketingActivity : AppCompatActivity(), View.OnClickListener {
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
        setContentView(R.layout.activity_ticketing)
        restoreState(savedInstanceState)

        showBackButton()
        showMovieIntroduce()
        setSpinnerConfig()
        setClickListener()
    }

    private fun setClickListener() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener(this@TicketingActivity)
        findViewById<Button>(R.id.btn_plus).setOnClickListener(this@TicketingActivity)
        findViewById<Button>(R.id.btn_ticketing).setOnClickListener(this@TicketingActivity)
    }

    private fun setSpinnerConfig() {
        setSpinnerAdapter()
        setSpinnerListener()
    }

    private fun setSpinnerAdapter() {
        findViewById<Spinner>(R.id.spinner_movie_date).adapter =
            movieDateAdapter.also { it.setNotifyOnChange(true) }
        findViewById<Spinner>(R.id.spinner_movie_time).adapter =
            movieDateAdapter.also { it.setNotifyOnChange(true) }
    }

    private fun setSpinnerListener() {
        setMovieTimeSpinnerListener()
        setMovieDateSpinnerListener()
    }

    private fun setMovieTimeSpinnerListener() {
        findViewById<Spinner>(R.id.spinner_movie_time).onItemSelectedListener =
            object : OnItemSelectedListener {
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
        findViewById<Spinner>(R.id.spinner_movie_date).onItemSelectedListener =
            object : OnItemSelectedListener {
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
        intent.getParcelableCompat<Movie>(MOVIE_KEY)?.run {
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
        findViewById<TextView>(R.id.tv_ticket_count).text = movieTicket.count.toString()
        findViewById<Spinner>(R.id.spinner_movie_date).setSelection(movieDates.indexOf(selectedDate))
        findViewById<Spinner>(R.id.spinner_movie_time).setSelection(movieTimes.indexOf(selectedTime))
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                movieTicket = (movieTicket.toDomain() - 1).toPresentation()
                findViewById<TextView>(R.id.tv_ticket_count).text = movieTicket.count.toString()
            }

            R.id.btn_plus -> {
                movieTicket = (movieTicket.toDomain() + 1).toPresentation()
                findViewById<TextView>(R.id.tv_ticket_count).text = movieTicket.count.toString()
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

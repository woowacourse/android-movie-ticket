package woowacourse.movie.presentation.ticketing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity
import woowacourse.movie.utils.formatMovieDate
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketingActivity : AppCompatActivity(), TicketingContract.View {
    private lateinit var ticketingPresenter: TicketingPresenter
    private val countText by lazy { findViewById<TextView>(R.id.tv_count) }
    private val movieDateSpinner by lazy { findViewById<Spinner>(R.id.sp_date) }
    private val movieDateAdapter: ArrayAdapter<LocalDate> by lazy {
        ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        )
    }
    private val movieTimeSpinner by lazy { findViewById<Spinner>(R.id.sp_time_slot) }
    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID)

        ticketingPresenter = TicketingPresenter(this)
        ticketingPresenter.loadMovieData(movieId)
        initializeButtons()
    }

    private fun initializeButtons() {
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val completeButton = findViewById<Button>(R.id.btn_choose_seat)

        minusButton.setOnClickListener {
            ticketingPresenter.decreaseCount()
        }

        plusButton.setOnClickListener {
            ticketingPresenter.increaseCount()
        }

        completeButton.setOnClickListener {
            ticketingPresenter.navigate()
        }
    }

    override fun displayMovieDetail(movie: Movie) {
        findViewById<ImageView>(R.id.iv_thumbnail).apply { setImageResource(movie.thumbnail) }
        findViewById<TextView>(R.id.tv_title).apply { text = movie.title }
        findViewById<TextView>(R.id.tv_date).apply {
            text =
                context.getString(
                    R.string.title_date,
                    formatMovieDate(movie.screeningDates.startDate),
                    formatMovieDate(movie.screeningDates.endDate),
                )
        }
        findViewById<TextView>(R.id.tv_running_time).apply {
            text = getString(R.string.title_running_time, movie.runningTime)
        }
        findViewById<TextView>(R.id.tv_introduction).apply { text = movie.introduction }
    }

    override fun displayTicketCount(count: Int) {
        countText.text = count.toString()
    }

    override fun setUpDateSpinners(
        screeningDates: List<LocalDate>,
        listener: AdapterView.OnItemSelectedListener,
    ) {
        movieDateAdapter.addAll(screeningDates)
        movieDateSpinner.adapter = movieDateAdapter
        movieDateSpinner.onItemSelectedListener = listener
    }

    override fun setUpTimeSpinners(
        screeningTimes: List<LocalTime>,
        savedTimePosition: Int?,
    ) {
        movieTimeAdapter.clear()
        movieTimeAdapter.addAll(screeningTimes.map { it.format(DateTimeFormatter.ofPattern("kk:mm")) })
        movieTimeSpinner.adapter = movieTimeAdapter
        savedTimePosition?.let { movieTimeSpinner.setSelection(it) }
    }

    override fun navigate(
        movieId: Int,
        count: Int,
    ) {
        val screeningDateTime = "${movieDateSpinner.selectedItem} ${movieTimeSpinner.selectedItem}"
        startActivity(
            SeatSelectionActivity.createIntent(this, movieId, count, screeningDateTime),
        )
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SAVED_COUNT, countText.text.toString().toInt())
        outState.putInt(KEY_SELECTED_TIME_POSITION, movieTimeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val savedCount = savedInstanceState.getInt(KEY_SAVED_COUNT, SAVED_DEFAULT_VALUE)
        if (savedCount != SAVED_DEFAULT_VALUE) {
            ticketingPresenter.updateCount(savedCount)
        }

        val savedTimePosition = savedInstanceState.getInt(KEY_SELECTED_TIME_POSITION)
        if (savedTimePosition != SAVED_DEFAULT_VALUE) {
            ticketingPresenter.updateSelectedTimePosition(savedTimePosition)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_DEFAULT_MOVIE_ID = -1
        const val KEY_SAVED_COUNT = "saved_count"
        const val SAVED_DEFAULT_VALUE = -1
        const val KEY_SELECTED_TIME_POSITION = "selected_time_position"

        fun createIntent(
            context: Context,
            movieId: Int,
        ): Intent {
            return Intent(context, TicketingActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
        }
    }
}

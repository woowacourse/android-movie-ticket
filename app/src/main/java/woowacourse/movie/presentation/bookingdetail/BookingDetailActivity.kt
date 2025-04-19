package woowacourse.movie.presentation.bookingdetail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.DateType
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.presentation.bookingcomplete.BookingCompleteActivity
import woowacourse.movie.presentation.bookingdetail.adapter.DateAdapter
import woowacourse.movie.presentation.bookingdetail.adapter.TimeAdapter
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailActivity : AppCompatActivity() {
    private lateinit var dateAdapter: DateAdapter
    private lateinit var timeAdapter: TimeAdapter
    private val movie: Movie by lazy { getMovieIntent() }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_time) }
    private val ticketCountView: TextView by lazy { findViewById(R.id.tv_booking_detail_count) }
    private var ticketCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()

        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = movie.title
        findViewById<TextView>(R.id.tv_booking_detail_date).text =
            getString(R.string.movies_movie_date_with_tilde, movie.startDate, movie.endDate)
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text = getString(R.string.movies_movie_running_time, movie.runningTime)
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(movie.poster)
        ticketCountView.text = ticketCount.toString()

        setupDateSpinner()
        setupTimeSpinner()

        setupDateSpinnerItemClickListener()
        setupTicketCountClickListeners()

        setupSelectCompleteClickListener()
    }

    private fun getMovieIntent(): Movie =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MOVIE_KEY, Movie::class.java) ?: Movie()
        } else {
            intent.getParcelableExtra<Movie>(MOVIE_KEY) ?: Movie()
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_DATE_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(TICKET_TIME_KEY, timeSpinner.selectedItemPosition)
        outState.putInt(TICKET_COUNT_KEY, ticketCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val ticketDate = savedInstanceState.getInt(TICKET_DATE_KEY)
        val ticketTime = savedInstanceState.getInt(TICKET_TIME_KEY)
        ticketCount = savedInstanceState.getInt(TICKET_COUNT_KEY)

        dateSpinner.setSelection(ticketDate)

        val selectedDate = DateType.from(LocalDate.parse(dateSpinner.selectedItem.toString()))
        timeAdapter.updateTimes(selectedDate)

        timeSpinner.setSelection(ticketTime)

        ticketCountView.text = ticketCount.toString()
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDateSpinner() {
        dateAdapter = DateAdapter(this, movie.startDate, movie.endDate)
        dateSpinner.adapter = dateAdapter
    }

    private fun setupTimeSpinner() {
        timeAdapter = TimeAdapter(this)
        timeAdapter.updateTimes(DateType.from(movie.startDate))
        timeSpinner.adapter = timeAdapter
    }

    private fun setupDateSpinnerItemClickListener() {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = parent?.getItemAtPosition(position) as LocalDate
                    val dateType = DateType.from(selectedDate)
                    timeAdapter.updateTimes(dateType)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun setupTicketCountClickListeners() {
        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            decreaseTicketCount()
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            increaseTicketCount()
        }
    }

    private fun decreaseTicketCount() {
        if (ticketCount > 0) {
            ticketCount--
            ticketCountView.text = ticketCount.toString()
        }
    }

    private fun increaseTicketCount() {
        ticketCount++
        ticketCountView.text = ticketCount.toString()
    }

    private fun setupSelectCompleteClickListener() {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            showSelectCompleteDialog()
        }
    }

    private fun showSelectCompleteDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.booking_detail_booking_check))
            .setMessage(getString(R.string.booking_detail_booking_check_description))
            .setPositiveButton(getString(R.string.booking_detail_booking_complete)) { _, _ ->
                navigateToBookingComplete()
            }.setNegativeButton(getString(R.string.booking_detail_booking_cancel), null)
            .setCancelable(false)
            .show()
    }

    private fun navigateToBookingComplete() {
        val selectedDate = LocalDate.parse(dateSpinner.selectedItem.toString())
        val selectedTime = MovieTime(LocalTime.parse(timeSpinner.selectedItem.toString()))

        val intent =
            BookingCompleteActivity.newIntent(
                context = this,
                bookingInfo =
                    BookingInfo(
                        movie = movie,
                        date = selectedDate,
                        movieTime = selectedTime,
                        count = ticketCount,
                    ),
            )

        startActivity(intent)
        finish()
    }

    companion object {
        const val MOVIE_KEY = "movie"
        const val TICKET_DATE_KEY = "ticket_date"
        const val TICKET_TIME_KEY = "ticket_time"
        const val TICKET_COUNT_KEY = "ticket_count"

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, movie)
            }
    }
}

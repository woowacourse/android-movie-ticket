package woowacourse.movie.booking.detail

import android.content.Context
import android.content.Intent
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
import woowacourse.movie.booking.complete.BookingCompleteActivity
import woowacourse.movie.domain.DateType
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.TicketInfo
import woowacourse.movie.domain.TicketQuantity
import woowacourse.movie.movies.MovieUiModel
import woowacourse.movie.util.dateRange
import woowacourse.movie.util.parcelableExtraWithVersion
import java.time.LocalDate

class BookingDetailActivity : AppCompatActivity() {
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var timeAdapter: TimeAdapter
    private lateinit var dateAdapter: DateAdapter
    private lateinit var movie: MovieUiModel
    private var ticketQuantity: TicketQuantity = TicketQuantity(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()

        movie = intent.parcelableExtraWithVersion(MOVIE_KEY, MovieUiModel::class.java) ?: return finish()

        bindMovie()
//
//        setupDateSpinner(movie)
//        setupTimeSpinner(movie)
//
//        setupDateSpinnerItemClickListener()
//        setupTicketCountClickListeners()

//        setupSelectCompleteClickListener(movie)
    }

    private fun bindMovie() {
        val title = movie.title
        val period = movie.periodText
        val runningTime = movie.runningTimeText
        val poster = movie.posterResId

        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_detail_date).text = period
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text = runningTime
//        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketQuantity.value.toString()
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(poster)
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

    private fun setupSelectCompleteClickListener(movie: Movie) {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            val selectedDate = dateSpinner.selectedItem.toString()
            val selectedTime = timeSpinner.selectedItem.toString()

            val ticketInfo =
                TicketInfo(
                    movie = movie,
                    date = selectedDate,
                    time = selectedTime,
                    quantity = ticketQuantity,
                )

            showSelectCompleteDialog(ticketInfo)
        }
    }

    private fun showSelectCompleteDialog(ticketInfo: TicketInfo) {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.booking_detail_booking_check))
            .setMessage(getString(R.string.booking_detail_booking_check_description))
            .setPositiveButton(getString(R.string.booking_detail_booking_complete)) { _, _ ->
                navigateToBookingComplete(ticketInfo)
            }.setNegativeButton(getString(R.string.booking_detail_booking_cancel), null)
            .setCancelable(false)
            .show()
    }

    private fun navigateToBookingComplete(ticketInfo: TicketInfo) {
        val intent =
            BookingCompleteActivity.newIntent(
                context = this,
                ticketInfo = ticketInfo,
            )

        startActivity(intent)
        finish()
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
        ticketQuantity = ticketQuantity.decrease()
        updateTicketCountText()
    }

    private fun increaseTicketCount() {
        ticketQuantity = ticketQuantity.increase()
        updateTicketCountText()
    }

    private fun updateTicketCountText() {
        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketQuantity.value.toString()
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

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupDateSpinner(movie: Movie) {
        dateSpinner = findViewById<Spinner>(R.id.sp_booking_detail_date)

        dateAdapter = DateAdapter(this, movie.dateRange(1))

        dateSpinner.adapter = dateAdapter
    }

    private fun setupTimeSpinner(movie: Movie) {
        timeSpinner = findViewById<Spinner>(R.id.sp_booking_detail_time)

        timeAdapter = TimeAdapter(this)
        timeAdapter.updateTimes(DateType.from(movie.startDate))

        timeSpinner.adapter = timeAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_DATE_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(TICKET_TIME_KEY, timeSpinner.selectedItemPosition)
        outState.putInt(TICKET_COUNT_KEY, ticketQuantity.value)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val ticketDate = savedInstanceState.getInt(TICKET_DATE_KEY)
        val ticketTime = savedInstanceState.getInt(TICKET_TIME_KEY)
        ticketQuantity = TicketQuantity(savedInstanceState.getInt(TICKET_COUNT_KEY))

        dateSpinner.setSelection(ticketDate)

        val selectedDate = DateType.from(LocalDate.parse(dateSpinner.selectedItem.toString()))
        timeAdapter.updateTimes(selectedDate)

        timeSpinner.setSelection(ticketTime)

        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketQuantity.value.toString()
    }

    companion object {
        const val MOVIE_KEY = "movie"
        const val TICKET_DATE_KEY = "ticket_date"
        const val TICKET_TIME_KEY = "ticket_time"
        const val TICKET_COUNT_KEY = "ticket_count"

        fun newIntent(
            context: Context,
            uiModel: MovieUiModel,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, uiModel)
            }
    }
}

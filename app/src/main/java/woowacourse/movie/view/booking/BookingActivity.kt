package woowacourse.movie.view.booking

import AdapterItemSelectedListener
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.PeopleCount
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.domain.model.TicketType
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.max

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        intent.getSerializable(KEY_MOVIE, Movie::class.java)?.let {
            initView(it, savedInstanceState)
        }
    }

    private fun initView(
        movie: Movie,
        savedInstanceState: Bundle?,
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initTitleView(movie.title)
        initPosterView(movie.poster)

        val startDate = movie.releaseDate.startDate
        val endDate = movie.releaseDate.endDate
        initReleaseDateView(startDate, endDate)

        initRunningTimeView(movie.runningTime)
        initButtonListener()

        restoreSavedState(savedInstanceState, startDate, endDate)
    }

    private fun initTitleView(title: String) {
        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = title
    }

    private fun initPosterView(poster: String) {
        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        val posterId = poster.toInt()
        moviePosterView.setImageResource(posterId)
    }

    private fun initReleaseDateView(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text =
            getString(R.string.text_date_period).format(
                StringFormatter.dotDateFormat(startDate),
                StringFormatter.dotDateFormat(endDate),
            )
    }

    private fun initRunningTimeView(runningTime: String) {
        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text = runningTime
    }

    private fun restoreSavedState(
        savedInstanceState: Bundle?,
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        var savedDatePosition: Int? = null
        var savedTimePosition: Int? = null
        savedInstanceState?.let {
            val count = it.getString(KEY_PEOPLE_COUNT)
            savedDatePosition = it.getInt(KEY_SELECTED_DATE_POSITION)
            savedTimePosition = it.getInt(KEY_SELECTED_TIME_POSITION)
            findViewById<TextView>(R.id.tv_people_count).text = count
        }

        setDateSpinner(startDate, endDate, savedDatePosition, savedTimePosition)
    }

    private fun setDateSpinner(
        startDate: LocalDate,
        endDate: LocalDate,
        savedDatePosition: Int?,
        savedTimePosition: Int?,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.sp_date)

        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )

            setSelection(savedDatePosition ?: 0)

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    setTimeSpinner(screeningBookingDates[pos], savedTimePosition)
                }
        }
    }

    private fun setTimeSpinner(
        selectedDate: LocalDate,
        savedPosition: Int?,
    ) {
        val timeSpinner: Spinner = findViewById(R.id.sp_time)

        val screeningTimes: List<LocalTime> =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningTimes,
                )
            setSelection(savedPosition ?: 0)
        }
    }

    private fun initButtonListener() {
        val increaseBtn = findViewById<Button>(R.id.btn_increase)
        val decreaseBtn = findViewById<Button>(R.id.btn_decrease)
        val bookingBtn = findViewById<Button>(R.id.btn_booking_complete)

        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        increaseBtn.setOnClickListener {
            val count = peopleCount.text.toString().toInt() + 1
            peopleCount.text = count.toString()
        }

        decreaseBtn.setOnClickListener {
            val count = max(MIN_BOOKING_PEOPLE_COUNT, peopleCount.text.toString().toInt() - 1)
            peopleCount.text = count.toString()
        }

        bookingBtn.setOnClickListener {
            showDialog(
                getString(R.string.text_booking_dialog_title),
                getString(R.string.text_booking_dialog_description),
            )
        }
    }

    private fun showDialog(
        title: String,
        description: String,
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(R.string.text_booking_dialog_positive_button) { _, _ ->
                moveToBookingCompleteActivity()
            }
            .setNegativeButton(R.string.text_booking_dialog_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun moveToBookingCompleteActivity() {
        val title = findViewById<TextView>(R.id.tv_title).text
        val date = findViewById<Spinner>(R.id.sp_date).selectedItem
        val time = findViewById<Spinner>(R.id.sp_time).selectedItem
        val count = findViewById<TextView>(R.id.tv_people_count).text.toString().toInt()
        val price = PeopleCount(count).ticketPrice(TicketType.GENERAL)

        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(KEY_BOOKING_MOVIE_TITLE, title)
                putExtra(KEY_BOOKING_DATE_TIME, "$date $time")
                putExtra(KEY_BOOKING_PEOPLE_COUNT, count)
                putExtra(KEY_BOOKING_TICKET_PRICE, price)
            }

        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val dateSpinner = findViewById<Spinner>(R.id.sp_date)
        val timeSpinner = findViewById<Spinner>(R.id.sp_time)
        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        outState.putString(KEY_PEOPLE_COUNT, peopleCount.text.toString())
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    companion object {
        private const val MIN_BOOKING_PEOPLE_COUNT = 1

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"

        const val KEY_BOOKING_MOVIE_TITLE = "BOOKING_MOVIE_TITLE"
        const val KEY_BOOKING_DATE_TIME = "BOOKING_DATE_TIME"
        const val KEY_BOOKING_PEOPLE_COUNT = "BOOKING_PEOPLE_COUNT"
        const val KEY_BOOKING_TICKET_PRICE = "BOOKING_TICKET_PRICE"
    }
}

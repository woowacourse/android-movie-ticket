package woowacourse.movie.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R
import woowacourse.movie.domain.model.DefaultPricingPolicy
import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieScheduler
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.ui.constant.IntentKeys
import woowacourse.movie.ui.util.intentSerializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Locale

class BookingActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_booking
    private lateinit var movie: Movie
    private var headCount: HeadCount = HeadCount()
    private lateinit var selectedDate: LocalDate
    private lateinit var selectedTime: LocalTime
    private val headCountView: TextView by lazy { findViewById(R.id.textview_headcount) }
    private var confirmDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!fetchMovieFromIntent()) return
        setupScreen(layoutRes)
        setupUI()
        bindListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(HEADCOUNT_KEY, headCount.value)
        outState.putString(DATE_KEY, selectedDate.toString())
        outState.putString(TIME_KEY, selectedTime.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        headCount = HeadCount(savedInstanceState.getInt(HEADCOUNT_KEY))
        updateHeadCount()
        selectedDate = LocalDate.parse(savedInstanceState.getString(DATE_KEY))
        selectedTime = LocalTime.parse(savedInstanceState.getString(TIME_KEY))
    }

    override fun onDestroy() {
        confirmDialog?.dismiss()
        confirmDialog = null
        super.onDestroy()
    }

    private fun fetchMovieFromIntent(): Boolean {
        val data = intent.intentSerializable(IntentKeys.MOVIE, Movie::class.java)
        if (data == null) {
            Toast.makeText(this, MOVIE_INTENT_ERROR, Toast.LENGTH_SHORT).show()
            finish()
            return false
        }
        movie = data
        return true
    }

    private fun setupUI() {
        displayMovieInfo()
        setupDateSpinner()
    }

    private fun bindListeners() {
        bindHeadCountButtonListeners()
        bindSelectButtonListener()
    }

    private fun displayMovieInfo() {
        val poster = findViewById<ImageView>(R.id.imageview_poster)
        poster.setImageResource(movie.posterRes)

        val title = findViewById<TextView>(R.id.textview_title)
        title.text = movie.title

        val screeningDate = findViewById<TextView>(R.id.textview_screeningdate)
        screeningDate.text =
            getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)

        val runningTime = findViewById<TextView>(R.id.textview_runningtime)
        runningTime.text = getString(R.string.runningTime_text, movie.runningTime.toString())
    }

    private fun bindHeadCountButtonListeners() {
        updateHeadCount()
        val increaseBtn = findViewById<Button>(R.id.button_increase)
        increaseBtn.setOnClickListener {
            headCount.increase()
            updateHeadCount()
        }
        val decreaseBtn = findViewById<Button>(R.id.button_decrease)
        decreaseBtn.setOnClickListener {
            headCount.decrease()
            updateHeadCount()
        }
    }

    private fun bindSelectButtonListener() {
        val selectBtn = findViewById<Button>(R.id.button_select)
        selectBtn.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun setupDateSpinner() {
        val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)
        val dateSpinner = findViewById<Spinner>(R.id.spinner_date)
        val dates = movieScheduler.getBookableDates()

        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dates
        )

        val index = if (::selectedDate.isInitialized) dates.indexOf(selectedDate) else 0
        dateSpinner.setSelection(index)

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedDate = dateSpinner.getItemAtPosition(position) as LocalDate
                setupTimeSpinner(movieScheduler, selectedDate)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupTimeSpinner(movieScheduler: MovieScheduler, selectedDate: LocalDate) {
        val timeSpinner = findViewById<Spinner>(R.id.spinner_time)
        val times = movieScheduler.getBookableTimes(selectedDate)

        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )

        val index = if (::selectedTime.isInitialized) times.indexOf(selectedTime) else 0
        timeSpinner.setSelection(index)

        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedTime = timeSpinner.getItemAtPosition(position) as LocalTime
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun showConfirmDialog() {
        if (confirmDialog == null) {
            initConfirmDialog()
        }
        confirmDialog?.show()
    }

    private fun initConfirmDialog() {
        confirmDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ -> onConfirm() }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
    }

    private fun onConfirm() {
        val movieTicket = MovieTicket(
            title = movie.title,
            screeningDateTime = LocalDateTime.of(selectedDate, selectedTime),
            headCount = headCount.value,
            pricingPolicy = DefaultPricingPolicy()
        )

        val intent = Intent(this, BookingSummaryActivity::class.java).apply {
            putExtra(IntentKeys.TICKET, movieTicket)
        }
        startActivity(intent)
    }

    private fun updateHeadCount() {
        headCountView.text = String.format(Locale.getDefault(), INTEGER_FORMAT, headCount.value)
    }

    companion object {
        private const val HEADCOUNT_KEY = "HeadCount"
        private const val DATE_KEY = "Date"
        private const val TIME_KEY = "Time"
        private const val INTEGER_FORMAT = "%d"
        private const val MOVIE_INTENT_ERROR = "[ERROR] 영화 정보에 대한 키 값이 올바르지 않습니다."
    }
}

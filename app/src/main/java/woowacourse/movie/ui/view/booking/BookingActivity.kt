package woowacourse.movie.ui.view.booking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieRepository
import woowacourse.movie.domain.policy.DefaultPricingPolicy
import woowacourse.movie.domain.schedule.MovieScheduler
import woowacourse.movie.domain.service.MovieTicketService
import woowacourse.movie.ui.adapter.MovieAdapter.Companion.setImage
import woowacourse.movie.ui.mapper.PosterMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private lateinit var headCountView: TextView
    private var date: LocalDate = LocalDate.now()
    private var time: LocalTime = LocalTime.now()
    private var headCount = HeadCount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) loadSavedInstanceState(savedInstanceState)
        if (!canLoadMovie()) return
        setupScreen()
        displayMovieInfo()
        setupTicketQuantityButtonListeners()
        setupSelectButtonListener()
        setupDateSpinner()
    }

    private fun canLoadMovie(): Boolean {
        val movieId = intent.getStringExtra(getString(R.string.movie_info_key))
        val foundMovie =
            movieId?.let {
                MovieRepository.getMovieById(it)
            }

        return if (foundMovie == null) {
            showErrorDialog()
            false
        } else {
            movie = foundMovie
            true
        }
    }

    private fun setupScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun displayMovieInfo() {
        val imagePoster = findViewById<ImageView>(R.id.poster)
        val posterRes = PosterMapper.mapMovieIdToDrawableRes(movie.id)
        imagePoster.setImage(posterRes)

        val title = findViewById<TextView>(R.id.title)
        title.text = movie.title

        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        screeningDate.text =
            getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)

        val runningTime = findViewById<TextView>(R.id.runningTime)
        runningTime.text = getString(R.string.runningTime_text, movie.runningTime.toString())
    }

    private fun setupTicketQuantityButtonListeners() {
        headCountView = findViewById(R.id.headCount)
        updateHeadCount()
        val increaseBtn = findViewById<Button>(R.id.increase)
        increaseBtn.setOnClickListener {
            increaseHeadCount()
        }
        val decreaseBtn = findViewById<Button>(R.id.decrease)
        decreaseBtn.setOnClickListener {
            decreaseHeadCount()
        }
    }

    private fun decreaseHeadCount() {
        headCount.decrease()
        updateHeadCount()
    }

    private fun increaseHeadCount() {
        headCount.increase()
        updateHeadCount()
    }

    private fun setupSelectButtonListener() {
        val selectBtn = findViewById<Button>(R.id.select)
        selectBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun setupDateSpinner() {
        val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)
        val dateSpinner = findViewById<Spinner>(R.id.dateSpinner)
        val dates = movieScheduler.getBookableDates()

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )

        val index = 0
        dateSpinner.setSelection(index)

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    date = dateSpinner.getItemAtPosition(position) as LocalDate
                    setupTimeSpinner(movieScheduler, date)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupTimeSpinner(
        movieScheduler: MovieScheduler,
        selectedDate: LocalDate,
    ) {
        val timeSpinner = findViewById<Spinner>(R.id.timeSpinner)
        val times = movieScheduler.getBookableTimes(selectedDate)

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )

        val index = 0
        timeSpinner.setSelection(index)

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    time = timeSpinner.getItemAtPosition(position) as LocalTime
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun showDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ -> onConfirm() }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    private fun showErrorDialog() {
        AlertDialog
            .Builder(this)
            .setMessage(R.string.movie_not_found_message)
            .setPositiveButton(R.string.confirm) { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }

    private fun onConfirm() {
        val movieTicketService = MovieTicketService(DefaultPricingPolicy())
        val movieTicket =
            movieTicketService.createMovieTicket(
                movie.id,
                LocalDateTime.of(date, time),
                headCount.getCount(),
            )

        val intent = Intent(this, BookingSummaryActivity::class.java)
        intent.putExtra(getString(R.string.ticket_info_key), movieTicket)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_HEAD_COUNT, headCount.getCount())
        outState.putString(KEY_DATE, date.toString())
        outState.putString(KEY_TIME, time.toString())
    }

    private fun loadSavedInstanceState(savedInstance: Bundle) {
        val restoredCount = savedInstance.getInt(KEY_HEAD_COUNT, headCount.getCount())
        headCount = HeadCount(restoredCount)
        date = LocalDate.parse(savedInstance.getString(KEY_DATE))
        time = LocalTime.parse(savedInstance.getString(KEY_TIME))
    }

    private fun updateHeadCount() {
        headCountView.text = headCount.getCount().toString()
    }

    companion object {
        private const val KEY_HEAD_COUNT = "HeadCount"
        private const val KEY_DATE = "Date"
        private const val KEY_TIME = "Time"
    }
}

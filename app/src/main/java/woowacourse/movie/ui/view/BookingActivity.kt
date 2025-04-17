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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.DefaultPricingPolicy
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieScheduler
import woowacourse.movie.model.MovieTicket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private var headCount: Int = 1
    private val movie: Movie by lazy {
        intent.intentSerializable(
            getString(R.string.movie_info_key),
            Movie::class.java
        )
    }
    private lateinit var date: LocalDate
    private lateinit var time: LocalTime
    private lateinit var headCountView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) loadSavedInstanceState(savedInstanceState)
        setupScreen()
        displayMovieInfo()
        bindTicketQuantityButtonListeners()
        bindSelectButtonListener()
        setupDateSpinner()
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
        val poster = findViewById<ImageView>(R.id.poster)
        poster.setImageResource(movie.posterRes)

        val title = findViewById<TextView>(R.id.title)
        title.text = movie.title

        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        screeningDate.text =
            getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)

        val runningTime = findViewById<TextView>(R.id.runningTime)
        runningTime.text = getString(R.string.runningTime_text, movie.runningTime.toString())
    }

    private fun bindTicketQuantityButtonListeners() {
        headCountView = findViewById(R.id.headCount)
        updateHeadCount()
        val increaseBtn = findViewById<Button>(R.id.increase)
        increaseBtn.setOnClickListener {
            headCount++
            updateHeadCount()
        }
        val decreaseBtn = findViewById<Button>(R.id.decrease)
        decreaseBtn.setOnClickListener {
            if (headCount > 1) {
                headCount--
                updateHeadCount()
            }
        }
    }

    private fun bindSelectButtonListener() {
        val selectBtn = findViewById<Button>(R.id.select)
        selectBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun setupDateSpinner() {
        val movieScheduler = MovieScheduler(movie.startScreeningDate, movie.endScreeningDate)
        val dateSpinner = findViewById<Spinner>(R.id.dateSpinner)
        val dates = movieScheduler.getBookableDates()

        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dates
        )

        val index = if (::date.isInitialized) dates.indexOf(date) else 0
        dateSpinner.setSelection(index)

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                date = dateSpinner.getItemAtPosition(position) as LocalDate
                setupTimeSpinner(movieScheduler, date)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupTimeSpinner(movieScheduler: MovieScheduler, selectedDate: LocalDate) {
        val timeSpinner = findViewById<Spinner>(R.id.timeSpinner)
        val times = movieScheduler.getBookableTimes(selectedDate)

        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )

        val index = if (::time.isInitialized) times.indexOf(time) else 0
        timeSpinner.setSelection(index)

        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                time = timeSpinner.getItemAtPosition(position) as LocalTime
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ -> onConfirm() }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    private fun onConfirm() {
        val movieTicket = MovieTicket(
            title = movie.title,
            screeningDateTime = LocalDateTime.of(date, time),
            headCount = headCount,
            pricingPolicy = DefaultPricingPolicy()
        )

        val intent = Intent(this, BookingSummaryActivity::class.java)
        intent.putExtra(getString(R.string.ticket_info_key), movieTicket)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(getString(R.string.headCount_tag), headCount)
        outState.putString(getString(R.string.date_tag), date.toString())
        outState.putString(getString(R.string.time_tag), time.toString())
    }

    private fun loadSavedInstanceState(savedInstance: Bundle) {
        headCount = savedInstance.getInt(getString(R.string.headCount_tag))
        date = LocalDate.parse(savedInstance.getString(getString(R.string.date_tag)))
        time = LocalTime.parse(savedInstance.getString(getString(R.string.time_tag)))
    }

    private fun updateHeadCount() {
        headCountView.text = headCount.toString()
    }
}

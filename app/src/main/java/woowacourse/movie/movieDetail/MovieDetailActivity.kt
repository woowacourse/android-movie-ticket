package woowacourse.movie.movieDetail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.seat.TheaterSeatActivity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private var ticketNum = 1
    private lateinit var presenter: MovieDetailContract.Presenter
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var dateAdapter: ArrayAdapter<String>
    private lateinit var timeAdapter: ArrayAdapter<String>
    private val plusButton: Button by lazy { findViewById(R.id.plus_button) }
    private val minusButton: Button by lazy { findViewById(R.id.minus_button) }
    private val seatConfirmationButton: Button by lazy { findViewById(R.id.seat_confirmation_button) }
    private val quantityText: TextView by lazy { findViewById(R.id.quantity_text_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        dateSpinner = findViewById(R.id.movie_date_spinner)
        timeSpinner = findViewById(R.id.movie_time_spinner)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = MovieDetailPresenter(
            view = this@MovieDetailActivity,
            intent = intent,
        )
        initSpinners()
        presenter.load()
        setupEventListeners()
    }

    private fun initSpinners() {
        val startDate = LocalDate.of(2024, 4, 1)
        val endDate = LocalDate.of(2024, 4, 28)
        val dates = generateDateRange(startDate, endDate)
        dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dates)
        dateSpinner.adapter = dateAdapter

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                updateTimeSpinner(dates[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateTimeSpinner(date: String) {
        val times = mutableListOf<String>()
        var time = if (isWeekend(date)) {
            9
        } else {
            10
        }
        while (time <= 24) {
            times.add("${time}:00")
            time += 2
        }
        timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, times)
        timeSpinner.adapter = timeAdapter
    }

    private fun generateDateRange(startDate: LocalDate, endDate: LocalDate): ArrayList<String> {
        val dates = ArrayList<String>()
        var date = startDate
        while (!date.isAfter(endDate)) {
            dates.add(date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
            date = date.plusDays(1)
        }
        return dates
    }

    private fun isWeekend(date: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val parsedDate = LocalDate.parse(date, formatter)
        return parsedDate.dayOfWeek == DayOfWeek.SATURDAY || parsedDate.dayOfWeek == DayOfWeek.SUNDAY
    }

    private fun setupEventListeners() {
        plusButton.setOnClickListener {
            presenter.onTicketPlusClicked(ticketNum)
        }

        minusButton.setOnClickListener {
            presenter.onTicketMinusClicked(ticketNum)
        }

        seatConfirmationButton.setOnClickListener {
            val theater = presenter.getTheater()
            val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Intent(this, TheaterSeatActivity::class.java).apply {
                    putExtra("ticketNum", ticketNum)
                    putExtra("Theater", theater)
                }
            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            navigateToPurchaseConfirmation(intent)
        }
    }

    override fun initializeViews(movieInfo: MovieInfo) {
        findViewById<TextView>(R.id.movie_title_large).text = movieInfo.title.toString()
        findViewById<TextView>(R.id.movie_release_date_large).text =
            movieInfo.releaseDate.toString()
        findViewById<TextView>(R.id.movie_running_time).text = movieInfo.runningTime.toString()
        findViewById<TextView>(R.id.movie_synopsis).text = movieInfo.synopsis.toString()
        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

    }

    override fun navigateToPurchaseConfirmation(intent: Intent) {
        startActivity(intent)
    }

    override fun onTicketCountChanged(ticketNum: Int) {
        this.ticketNum = ticketNum
        quantityText.text = this.ticketNum.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}

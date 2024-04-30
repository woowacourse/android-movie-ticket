package woowacourse.movie.movieDetail

import android.annotation.SuppressLint
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.seat.TheaterSeatActivity
import java.time.LocalDate

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
        presenter.load()
        setupEventListeners()
        presenter.generateDateRange(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 28))

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

    override fun onTicketCountChanged(currentTicketNum: Int) {
        quantityText.text = currentTicketNum.toString()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateDateAdapter(dates: List<String>) {
        dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dates)
        dateSpinner.adapter = dateAdapter
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                presenter.updateTimeSpinner(dates[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun updateTimeAdapter(times: List<String>) {
        timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, times)
        timeSpinner.adapter = timeAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    @SuppressLint("NewApi")
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
                    putExtra("ticketNum", presenter.getTicketNum())
                    putExtra("Theater", theater)
                }
            } else {
                Intent(this, TheaterSeatActivity::class.java).apply {
                    putExtra("ticketNum", presenter.getTicketNum())
                    putExtra("Theater", theater)
                }
            }
            navigateToPurchaseConfirmation(intent)
        }
    }
}

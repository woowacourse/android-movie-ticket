package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.adapter.DateSpinnerAdapter
import woowacourse.movie.adapter.TimeSpinnerAdapter
import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.presenter.MovieDetailPresenter
import woowacourse.movie.uimodel.SeatPlan
import woowacourse.movie.uimodel.format
import java.time.LocalDateTime

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private var ticketNum: Int = 1
    private val numberOfPurchases by lazy {
        findViewById<TextView>(R.id.quantity_text_view)
    }
    lateinit var presenter: MovieDetailPresenter
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        presenter = MovieDetailPresenter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)
        dateSpinner = findViewById<Spinner>(R.id.movie_screening_date_spinner)
        timeSpinner = findViewById<Spinner>(R.id.movie_screening_time_spinner)

        val movieId = intent.getIntExtra("MovieId", -1)
        presenter.loadMovie(movieId)
        ticketPlusButton.setOnClickListener {
            presenter.plusTicketNum()
        }
        ticketMinusButton.setOnClickListener {
            presenter.minusTicketNum()
        }
        ticketBuyButton.setOnClickListener {
            presenter.confirm()
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun displayMovie(movie: Movie) {
        val movieDetail = movie.movieDetail
        findViewById<TextView>(R.id.movie_title_large).text =
            movieDetail.title.format()
        findViewById<TextView>(R.id.movie_release_date_large).text =
            movie.screeningPeriod.format()
        findViewById<TextView>(R.id.movie_running_time).text =
            movieDetail.runningTime.format()
        findViewById<TextView>(R.id.movie_synopsis).text =
            movieDetail.synopsis.format()
    }

    override fun displayScreeningDates(dates: List<ScreeningDate>) {
        dateSpinner.adapter = DateSpinnerAdapter(dates)
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectScreeningDate(dates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun displayScreeningTimes(times: List<ScreeningDateTime>) {
        timeSpinner.adapter = TimeSpinnerAdapter(times)
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectScreeningTime(times[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun displayTicketNum(ticketNum: Int) {
        this.ticketNum = ticketNum
        numberOfPurchases.text = this.ticketNum.toString()
    }

    override fun navigateToSeatSelection(
        movieId: Int,
        ticketNum: Int,
        reservedDateTime: LocalDateTime,
    ) {
        val seatPlan = SeatPlan(movieId, ticketNum, reservedDateTime)
        val intent =
            Intent(this, SeatSelectionActivity::class.java).apply {
                putExtra("SeatPlan", seatPlan)
            }
        startActivity(intent)
    }
}

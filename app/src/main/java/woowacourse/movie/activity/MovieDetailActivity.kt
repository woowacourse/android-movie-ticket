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
import woowacourse.movie.IntentKeys.MOVIE_ID
import woowacourse.movie.IntentKeys.SEAT_PLAN
import woowacourse.movie.R
import woowacourse.movie.adapter.DateSpinnerAdapter
import woowacourse.movie.adapter.TimeSpinnerAdapter
import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.presenter.MovieDetailPresenter
import woowacourse.movie.uiModels.SeatPlan
import woowacourse.movie.uiModels.movie.MovieDetail
import woowacourse.movie.uiModels.movie.toMovieDetail
import java.time.LocalDateTime

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {

    private lateinit var presenter: MovieDetailPresenter
    private var movieId: Int = -1

    private val ticketCounterText: TextView by lazy {
        findViewById<TextView>(R.id.quantity_text_view)
    }
    private val timeSpinner: Spinner by lazy {
        findViewById<Spinner>(R.id.movie_screening_time_spinner)
    }
    private val dateSpinner: Spinner by lazy {
        findViewById<Spinner>(R.id.movie_screening_date_spinner)
    }
    private val movieDetailViews by lazy {
        MovieDetailViews(
            findViewById<TextView>(R.id.movie_title_large),
            findViewById<TextView>(R.id.movie_release_date_large),
            findViewById<TextView>(R.id.movie_running_time),
            findViewById<TextView>(R.id.movie_synopsis),
        )
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.movie_detail)
            movieId = savedInstanceState?.getInt(MOVIE_ID) ?: intent.getIntExtra(MOVIE_ID, -1)
            presenter = MovieDetailPresenter(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            findViewById<ImageView>(R.id.movie_thumbnail)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

            val ticketPlusButton = findViewById<Button>(R.id.plus_button)
            val ticketMinusButton = findViewById<Button>(R.id.minus_button)
            val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)

            // val movieId = intent.getIntExtra("MovieId", -1)
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

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putInt(MOVIE_ID, movieId)
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

        override fun displayMovie(movieDetail: MovieDetail) {
            movieDetailViews.set(movieDetail)
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
            ticketCounterText.text = ticketNum.toString()
        }

        override fun navigateToSeatSelection(
            movieId: Int,
            ticketNum: Int,
            reservedDateTime: LocalDateTime,
        ) {
            val seatPlan = SeatPlan(movieId, ticketNum, reservedDateTime)
            val intent =
                Intent(this, SeatSelectionActivity::class.java).apply {
                    putExtra(SEAT_PLAN, seatPlan)
                }
            startActivity(intent)
        }
    }

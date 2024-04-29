package woowacourse.movie.result.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.main.view.MovieMainActivity
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.MovieResultPresenter
import woowacourse.movie.result.presenter.contract.MovieResultContract
import woowacourse.movie.util.Formatter.formatPrice
import woowacourse.movie.util.Formatter.formatRow
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private lateinit var resultTitle: TextView
    private lateinit var resultDate: TextView
    private lateinit var resultTime: TextView
    private lateinit var resultCount: TextView
    private lateinit var resultSeats: TextView
    private lateinit var resultPrice: TextView

    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpBackButton()

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.loadMovieTicket(
            intent.getLongExtra(KEY_MOVIE_ID, INVALID_VALUE_MOVIE_ID),
            intent.getStringExtra(KEY_MOVIE_DATE) ?: INVALID_VALUE_MOVIE_DATE,
            intent.getStringExtra(KEY_MOVIE_TIME) ?: INVALID_VALUE_MOVIE_TIME,
            intent.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT),
            intent.getStringExtra(KEY_MOVIE_SEATS) ?: INVALID_VALUE_MOVIE_SEATS,
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun displayMovieTicket(movieTicketData: MovieTicket?) {
        movieTicketData?.let { movieTicket ->
            resultTitle.text = movieTicket.title
            resultDate.text = movieTicket.date.toString()
            resultTime.text = movieTicket.time.toString()
            resultCount.text = movieTicket.seats.count.toString()
            resultSeats.text =
                movieTicket.seats.selectedSeats.joinToString(", ") { seat ->
                    getString(R.string.seat, formatRow(seat.row), seat.column.toString())
                }
            resultPrice.text = formatPrice(movieTicket.seats.totalPrice())
        }
    }

    private fun setUpViewById() {
        resultTitle = findViewById(R.id.resultTitle)
        resultDate = findViewById(R.id.resultDate)
        resultTime = findViewById(R.id.resultTime)
        resultCount = findViewById(R.id.resultReservCount)
        resultSeats = findViewById(R.id.resultSeats)
        resultPrice = findViewById(R.id.resultReservPrice)
    }

    private fun setUpBackButton() {
        val onBackPressedDispatcher = onBackPressedDispatcher
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(this@MovieResultActivity, MovieMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
            },
        )
    }
}

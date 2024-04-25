package woowacourse.movie.result.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.MovieResultPresenter
import woowacourse.movie.result.presenter.contract.MovieResultContract
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_TITLE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TITLE
import java.text.DecimalFormat

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

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.loadMovieTicket(
            intent.getStringExtra(KEY_MOVIE_TITLE) ?: INVALID_VALUE_MOVIE_TITLE,
            intent.getStringExtra(KEY_MOVIE_DATE) ?: INVALID_VALUE_MOVIE_DATE,
            intent.getStringExtra(KEY_MOVIE_TIME) ?: INVALID_VALUE_MOVIE_TIME,
            intent.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT),
            intent.getStringExtra(KEY_MOVIE_SEATS) ?: INVALID_VALUE_MOVIE_SEATS,
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun displayMovieTicket(movieTicketData: MovieTicket?) {
        movieTicketData?.let { movieTicket ->
            resultTitle.text = movieTicket.title
            resultDate.text = movieTicket.date.toString()
            resultTime.text = movieTicket.time.toString()
            resultCount.text = movieTicket.count.toString()
            resultSeats.text =
                movieTicket.seats.joinToString(", ") { seat ->
                    getString(R.string.seat, seat.row, seat.column)
                }
            resultPrice.text = DecimalFormat("#,###").format(movieTicket.totalPrice)
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
}

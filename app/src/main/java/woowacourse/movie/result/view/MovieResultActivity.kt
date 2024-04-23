package woowacourse.movie.result.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.MovieResultPresenter
import woowacourse.movie.result.presenter.contract.MovieResultContract
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private lateinit var completeTitleTextView: TextView
    private lateinit var completeDateTextView: TextView
    private lateinit var completeReservationCountTextView: TextView
    private lateinit var completeReservationPriceTextView: TextView

    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.loadMovieTicket(
            intent.getLongExtra(KEY_MOVIE_ID, INVALID_VALUE_MOVIE_ID),
            intent.getStringExtra(KEY_MOVIE_DATE) ?: INVALID_VALUE_MOVIE_DATE,
            intent.getStringExtra(KEY_MOVIE_TIME) ?: INVALID_VALUE_MOVIE_TIME,
            intent.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT),
        )
    }

    override fun displayMovieTicket(movieTicketData: MovieTicket?) {
        movieTicketData?.let { movieTicket ->
            completeTitleTextView.text = movieTicket.title
            completeDateTextView.text = movieTicket.date
            completeReservationCountTextView.text = "${movieTicket.count}"
            completeReservationPriceTextView.text = movieTicketData.formatPrice()
        }
    }

    private fun setUpViewById() {
        completeTitleTextView = findViewById(R.id.resultTitle)
        completeDateTextView = findViewById(R.id.resultDate)
        completeReservationCountTextView = findViewById(R.id.resultReservCount)
        completeReservationPriceTextView = findViewById(R.id.resultReservPrice)
    }
}

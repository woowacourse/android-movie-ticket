package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.MovieResultContract
import woowacourse.movie.presenter.MovieResultPresenter
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstant.INVALID_VALUE_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstant.KEY_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.formatTimestamp

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private lateinit var completeTitleTextView: TextView
    private lateinit var completeDateTextView: TextView
    private lateinit var completeReservationCountTextView: TextView
    private lateinit var completeReservationPriceTextView: TextView
    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        completeTitleTextView = findViewById(R.id.resultTitle)
        completeDateTextView = findViewById(R.id.resultDate)
        completeReservationCountTextView = findViewById(R.id.resultReservCount)
        completeReservationPriceTextView = findViewById(R.id.resultReservPrice)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.display(
            intent.getLongExtra(KEY_MOVIE_ID, INVALID_VALUE_MOVIE_ID),
            intent.getIntExtra(KEY_MOVIE_RESERVATION_COUNT, INVALID_VALUE_MOVIE_RESERVATION_COUNT),
        )
    }

    override fun onInitView(movieTicket: MovieTicket?) {
        movieTicket?.let {
            completeTitleTextView.text = it.title
            completeDateTextView.text = formatTimestamp(it.date)
            completeReservationCountTextView.text = "${it.count}"
            completeReservationPriceTextView.text = formatCurrency(it.price)
        } ?: {
            setResult(MovieErrorCode.INVALID_MOVIE_ID.code)
            finish()
        }
    }
}

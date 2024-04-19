package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.MovieResultContract
import woowacourse.movie.presenter.MovieResultPresenter
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.formatTimestamp

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private val completeTitleTextView: TextView by lazy { findViewById(R.id.resultTitle) }
    private val completeDateTextView: TextView by lazy { findViewById(R.id.resultDate) }
    private val completeReservationCountTextView: TextView by lazy { findViewById(R.id.resultReservCount) }
    private val completeReservationPriceTextView: TextView by lazy { findViewById(R.id.resultReservPrice) }
    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.display(
            intent.getLongExtra(EXTRA_MOVIE_ID, NOT_FOUND_MOVIE_ID),
            intent.getIntExtra(
                EXTRA_MOVIE_RESERVATION_COUNT,
                NOT_FOUND_MOVIE_RESERVATION_COUNT,
            ),
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

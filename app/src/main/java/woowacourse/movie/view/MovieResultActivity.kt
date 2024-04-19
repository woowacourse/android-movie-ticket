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
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERV_COUNT
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
            intent.getLongExtra(EXTRA_MOVIE_ID, 0),
            intent.getIntExtra(
                EXTRA_MOVIE_RESERV_COUNT,
                0,
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

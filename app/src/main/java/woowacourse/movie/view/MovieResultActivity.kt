package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.MovieResultContract
import woowacourse.movie.presenter.MovieResultPresenter
import woowacourse.movie.utils.MovieErrorCode
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

        completeTitleTextView = findViewById(R.id.resultTitle)
        completeDateTextView = findViewById(R.id.resultDate)
        completeReservationCountTextView = findViewById(R.id.resultReservationCount)
        completeReservationPriceTextView = findViewById(R.id.resultReservationPrice)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.display(intent.getLongExtra("movieId", 0), intent.getIntExtra("movieReservationCount", 0))
    }

    override fun onInitView(movieTicket: MovieTicket?) {
        movieTicket?.let {
            completeTitleTextView.text = it.title
            completeDateTextView.text = formatTimestamp(it.date)
            completeReservationCountTextView.text = "${it.count}명"
            completeReservationPriceTextView.text = "${formatCurrency(it.price)}원"
        } ?: {
            setResult(MovieErrorCode.INVALID_MOVIE_ID.value)
            finish()
        }
    }
}

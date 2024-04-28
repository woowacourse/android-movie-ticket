package woowacourse.movie.presentation.result

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SEATS_ID_LIST
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.formatLocalDateTime
import woowacourse.movie.utils.mapNumberToLetter

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private val completeTitleTextView: TextView by lazy { findViewById(R.id.resultTitle) }
    private val completeDateTextView: TextView by lazy { findViewById(R.id.resultDate) }
    private val completeReservationCountTextView: TextView by lazy { findViewById(R.id.resultReservCount) }
    private val completeReservationPriceTextView: TextView by lazy { findViewById(R.id.resultReservPrice) }
    private val completeReservationSeasTextView: TextView by lazy { findViewById(R.id.resultSeats) }
    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.loadResult(
            intent.getLongExtra(EXTRA_MOVIE_ID, NOT_FOUND_MOVIE_ID),
            intent.getLongExtra(EXTRA_MOVIE_SCREEN_DATE_TIME_ID, NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID),
            intent.getLongArrayExtra(EXTRA_MOVIE_SEATS_ID_LIST)?.toList() ?: emptyList(),
        )
    }

    override fun onUpdateView(resultUiModel: ResultUiModel) {
        with(resultUiModel) {
            completeTitleTextView.text = this.movieTitle
            completeDateTextView.text = formatLocalDateTime(this.localDateTime)
            completeReservationCountTextView.text = "${this.reservationCount}"
            completeReservationPriceTextView.text = formatCurrency(this.totalPrice)
            completeReservationSeasTextView.text = this.seats.map { mapNumberToLetter(it!!.number) }.joinToString(separator = ",")
        }
    }

    override fun onError(errorCode: MovieErrorCode) {
        // 에러 발생 시에, 이전 액티비티로 이동하며 메세지 전달
        setResult(errorCode.code)
        finish()
    }
}

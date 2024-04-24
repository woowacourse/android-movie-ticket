package woowacourse.movie.result.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.result.presenter.MovieResultPresenter
import woowacourse.movie.result.presenter.contract.MovieResultContract
import woowacourse.movie.util.MovieIntentConstant
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME

class MovieResultActivity : AppCompatActivity(), MovieResultContract.View {
    private lateinit var resultTitle: TextView
    private lateinit var resultDate: TextView
    private lateinit var resultTime: TextView
    private lateinit var resultCount: TextView
    private lateinit var resultPrice: TextView

    private lateinit var movieResultPresenter: MovieResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_result)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieResultPresenter = MovieResultPresenter(this)
        movieResultPresenter.loadMovieTicket(
            intent.getLongExtra(KEY_MOVIE_ID, INVALID_VALUE_MOVIE_ID),
            intent.getStringExtra(MovieIntentConstant.KEY_MOVIE_DATE)
                ?: MovieIntentConstant.INVALID_VALUE_MOVIE_DATE,
            intent.getStringExtra(KEY_MOVIE_TIME) ?: INVALID_VALUE_MOVIE_TIME,
            intent.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT),
        )
    }

    override fun displayMovieTicket(movieTicketData: MovieTicket?) {
        movieTicketData?.let { movieTicket ->
            resultTitle.text = movieTicket.title
            resultDate.text = movieTicket.date.toString().replace('-', '.')
            resultTime.text = movieTicket.time.toString()
            resultCount.text = movieTicket.count.toString()
            resultPrice.text = movieTicketData.formatPrice()
        }
    }

    private fun setUpViewById() {
        resultTitle = findViewById(R.id.resultTitle)
        resultDate = findViewById(R.id.resultDate)
        resultTime = findViewById(R.id.resultTime)
        resultCount = findViewById(R.id.resultReservCount)
        resultPrice = findViewById(R.id.resultReservPrice)
    }
}

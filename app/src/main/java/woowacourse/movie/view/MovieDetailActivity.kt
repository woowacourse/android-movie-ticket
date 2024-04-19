package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MovieDetailContract
import woowacourse.movie.presenter.MovieDetailPresenter
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_ID
import woowacourse.movie.utils.formatTimestamp

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var reservationCompleteActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieDetailPresenter: MovieDetailPresenter

    private val detailImage: ImageView by lazy { findViewById(R.id.detailImage) }
    private val detailTitle: TextView by lazy { findViewById(R.id.detailTitle) }
    private val detailDate: TextView by lazy { findViewById(R.id.detailDate) }
    private val detailRunningTime: TextView by lazy { findViewById(R.id.detailRunningTime) }
    private val detailDescription: TextView by lazy { findViewById(R.id.detailDescription) }
    private val reservationCount: TextView by lazy { findViewById(R.id.detailReservCount) }
    private val minusButton: Button by lazy { findViewById(R.id.detailMinusBtn) }
    private val plusButton: Button by lazy { findViewById(R.id.detailPlusBtn) }
    private val reservationCompleteButton: Button by lazy { findViewById(R.id.detailReservCompleteBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reservationCompleteActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.code) {
                    Toast.makeText(this, MovieErrorCode.INVALID_MOVIE_ID.msg, Toast.LENGTH_SHORT).show()
                }
            }
        movieDetailPresenter = MovieDetailPresenter(this)

        movieDetailPresenter.display(intent.getLongExtra(MovieIntentConstants.EXTRA_MOVIE_ID, NOT_FOUND_MOVIE_ID))
    }

    override fun onInitView(movieData: Movie?) {
        movieData?.let { movie ->
            detailImage.setImageResource(movie.thumbnail)
            detailTitle.text = movie.title
            detailDate.text = formatTimestamp(movie.date)
            detailRunningTime.text = "${movie.runningTime}"
            detailDescription.text = movie.description

            minusButton.setOnClickListener {
                movieDetailPresenter.minusReservationCount()
            }
            plusButton.setOnClickListener {
                movieDetailPresenter.plusReservationCount()
            }
            reservationCompleteButton.setOnClickListener {
                movieDetailPresenter.reservation(movie.id)
            }
        } ?: {
            setResult(MovieErrorCode.INVALID_MOVIE_ID.code)
            finish()
        }
    }

    override fun onCountUpdate(count: Int) {
        reservationCount.text = (count).toString()
    }

    override fun onReservationComplete(
        id: Long,
        count: Int,
    ) {
        Intent(this, MovieResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, id)
            putExtra(EXTRA_MOVIE_RESERVATION_COUNT, count)
            reservationCompleteActivityResultLauncher.launch(this)
        }
    }
}

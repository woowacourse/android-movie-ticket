package woowacourse.movie.detail.view

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
import woowacourse.movie.detail.presenter.MovieDetailContract
import woowacourse.movie.detail.presenter.MovieDetailPresenter
import woowacourse.movie.model.Movie
import woowacourse.movie.result.view.MovieResultActivity
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

        savedInstanceState?.let {
            reservationCount.text = it.getInt(EXTRA_MOVIE_RESERVATION_COUNT).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_MOVIE_RESERVATION_COUNT, reservationCount.text.toString().toInt())
    }

    override fun onInitView(movie: Movie) {
        with(movie) {
            detailImage.setImageResource(this.thumbnail)
            detailTitle.text = this.title
            detailDate.text = formatTimestamp(this.date)
            detailRunningTime.text = "${this.runningTime}"
            detailDescription.text = this.description
            minusButton.setOnClickListener {
                movieDetailPresenter.minusReservationCount()
            }
            plusButton.setOnClickListener {
                movieDetailPresenter.plusReservationCount()
            }
            reservationCompleteButton.setOnClickListener {
                movieDetailPresenter.reservation(this.id)
            }
        }
    }

    override fun onCountUpdate(count: Int) {
        reservationCount.text = (count).toString()
    }

    override fun onError(errorCode: MovieErrorCode) {
        // 에러 발생 시에, 이전 액티비티로 이동하며 메세지 전달
        setResult(errorCode.code)
        finish()
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

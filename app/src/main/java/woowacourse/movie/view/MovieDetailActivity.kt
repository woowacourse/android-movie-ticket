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
import woowacourse.movie.utils.formatTimestamp

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var reservationCompleteActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieDetailPresenter: MovieDetailPresenter

    private lateinit var detailImage: ImageView
    private lateinit var detailTitle: TextView
    private lateinit var detailDate: TextView
    private lateinit var detailRunningTime: TextView
    private lateinit var detailDescription: TextView
    private lateinit var reservationCount: TextView
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var reservationCompleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        reservationCompleteActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.value) {
                    Toast.makeText(this, "올바르지 않은 ID 입니다", Toast.LENGTH_SHORT).show()
                }
            }
        movieDetailPresenter = MovieDetailPresenter(this)

        detailImage = findViewById(R.id.detailImage)
        detailTitle = findViewById(R.id.detailTitle)
        detailDate = findViewById(R.id.detailDate)
        detailRunningTime = findViewById(R.id.detailRunningTime)
        detailDescription = findViewById(R.id.detailDescription)
        reservationCount = findViewById(R.id.reservationCount)
        minusButton = findViewById(R.id.minus)
        plusButton = findViewById(R.id.plus)
        reservationCompleteButton = findViewById(R.id.reservationComplete)

        movieDetailPresenter.display(intent.getLongExtra("movieId", 0))
    }

    override fun onInitView(movieData: Movie?) {
        movieData?.let { movie ->
            detailImage.setImageResource(movie.thumbnail)
            detailTitle.text = movie.title
            detailDate.text = formatTimestamp(movie.date)
            detailRunningTime.text = "${movie.runningTime}분"
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
            setResult(MovieErrorCode.INVALID_MOVIE_ID.value)
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
            putExtra("movieId", id)
            putExtra("movieReservationCount", count)
            reservationCompleteActivityResultLauncher.launch(this)
        }
    }
}

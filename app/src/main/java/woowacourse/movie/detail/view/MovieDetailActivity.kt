package woowacourse.movie.detail.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.detail.presenter.MovieDetailPresenter
import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieReservationCount
import woowacourse.movie.result.view.MovieResultActivity
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_RESERVATION_COUNT
import woowacourse.movie.util.formatTimestamp

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var detailImage: ImageView
    private lateinit var detailTitle: TextView
    private lateinit var detailDate: TextView
    private lateinit var detailRunningTime: TextView
    private lateinit var detailDescription: TextView
    private lateinit var reservationCount: TextView
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var reservationCompleteButton: Button

    private lateinit var movieDetailPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieDetailPresenter =
            MovieDetailPresenter(this, savedInstanceState?.getInt(KEY_MOVIE_RESERVATION_COUNT))
        movieDetailPresenter.loadMovieDetail(
            intent.getLongExtra(
                KEY_MOVIE_ID,
                INVALID_VALUE_MOVIE_ID,
            ),
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = reservationCount.text.toString().toInt()
        outState.putInt(KEY_MOVIE_RESERVATION_COUNT, count)
    }

    override fun displayMovieDetail(
        movieData: Movie?,
        movieReservationCount: MovieReservationCount,
    ) {
        movieData?.let { movie ->
            detailImage.setImageResource(movie.thumbnail)
            detailTitle.text = movie.title
            detailDate.text = formatTimestamp(movie.date)
            detailRunningTime.text = "${movie.runningTime}"
            detailDescription.text = movie.description
            reservationCount.text = movieReservationCount.count.toString()

            minusButton.setOnClickListener {
                movieDetailPresenter.minusReservationCount()
            }
            plusButton.setOnClickListener {
                movieDetailPresenter.plusReservationCount()
            }
            reservationCompleteButton.setOnClickListener {
                movieDetailPresenter.reserveMovie(movie.id)
            }
        }
    }

    override fun updateCount(count: Int) {
        reservationCount.text = count.toString()
    }

    override fun navigateToResultView(
        id: Long,
        count: Int,
    ) {
        Intent(this, MovieResultActivity::class.java).apply {
            putExtra(KEY_MOVIE_ID, id)
            putExtra(KEY_MOVIE_RESERVATION_COUNT, count)
            startActivity(this)
        }
    }

    private fun setUpViewById() {
        detailImage = findViewById(R.id.detailImage)
        detailTitle = findViewById(R.id.detailTitle)
        detailDate = findViewById(R.id.detailDate)
        detailRunningTime = findViewById(R.id.detailRunningTime)
        detailDescription = findViewById(R.id.detailDescription)
        reservationCount = findViewById(R.id.detailReservCount)
        minusButton = findViewById(R.id.detailMinusBtn)
        plusButton = findViewById(R.id.detailPlusBtn)
        reservationCompleteButton = findViewById(R.id.seatSelectionBtn)
    }
}

package woowacourse.movie.presentation.view

import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.presenter.MovieDetailPresenterImpl

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private lateinit var movieDetailPresenter: MovieDetailContract.Presenter
    private val reservationCountTextView: TextView by lazy {
        findViewById(R.id.reservationCount)
    }
    private val reserveButton: Button by lazy {
        findViewById(R.id.reserveButton)
    }

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val posterImageId = intent.getIntExtra(INTENT_POSTER_IMAGE_ID, defaultPosterImageId)
        val title = intent.getStringExtra(INTENT_TITLE) ?: ""
        val screeningDate = intent.getStringExtra(INTENT_SCREENING_DATE) ?: ""
        val runningTime = intent.getIntExtra(INTENT_RUNNING_TIME, 0)
        val summary = intent.getStringExtra(INTENT_SUMMARY) ?: ""

        movieDetailPresenter = MovieDetailPresenterImpl(this, title, screeningDate)

        showMovieDetail(posterImageId, title, screeningDate, runningTime, summary)
        setupReservationCountButton()
        moveToReservationResult()
    }

    override fun showMovieDetail(
        posterImageId: Int,
        title: String,
        screeningDate: String,
        runningTime: Int,
        summary: String,
    ) {
        findViewById<ImageView>(R.id.posterImage).setImageResource(posterImageId)
        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = getString(R.string.screening_date_format, screeningDate)
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, runningTime)
        findViewById<TextView>(R.id.summary).text = summary
    }

    private fun setupReservationCountButton() {
        findViewById<TextView>(R.id.minusButton).setOnClickListener {
            movieDetailPresenter.minusReservationCount()
        }

        findViewById<TextView>(R.id.plusButton).setOnClickListener {
            movieDetailPresenter.plusReservationCount()
        }
    }

    override fun showReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }

    override fun moveToReservationResult() {
        reserveButton.setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra(ReservationResultActivity.INTENT_TITLE, movieDetailPresenter.movieTicket.movieTitle)
            intent.putExtra(ReservationResultActivity.INTENT_SCREENING_DATE, movieDetailPresenter.movieTicket.screeningDate)
            intent.putExtra(ReservationResultActivity.INTENT_RESERVATION_COUNT, movieDetailPresenter.movieTicket.count)
            intent.putExtra(ReservationResultActivity.INTENT_TOTAL_PRICE, movieDetailPresenter.movieTicket.totalPrice())
            startActivity(intent)
        }
    }

    companion object {
        val defaultPosterImageId = R.drawable.img_noimg
        const val INTENT_POSTER_IMAGE_ID = "posterImageId"
        const val INTENT_TITLE = "title"
        const val INTENT_SCREENING_DATE = "screeningDate"
        const val INTENT_RUNNING_TIME = "runningTime"
        const val INTENT_SUMMARY = "summary"
    }
}

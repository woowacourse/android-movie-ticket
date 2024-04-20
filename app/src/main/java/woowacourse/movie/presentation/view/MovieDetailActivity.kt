package woowacourse.movie.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.repository.InMemoryMovieTicketRepository
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.dto.ReservationData
import woowacourse.movie.presentation.presenter.MovieDetailPresenterImpl

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private var movieDetailPresenter: MovieDetailContract.Presenter? = null
    private val reservationCountTextView: TextView by lazy {
        findViewById(R.id.reservationCount)
    }
    private val reserveButton: Button by lazy {
        findViewById(R.id.reserveButton)
    }

    private val minusButton: TextView by lazy {
        findViewById(R.id.minusButton)
    }

    private val plusButton: TextView by lazy {
        findViewById(R.id.plusButton)
    }

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val posterImageId = intent.getIntExtra(EXTRA_POSTER_IMAGE_SRC, 0)
        val title = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val screeningDate = intent.getStringExtra(EXTRA_SCREENING_DATE) ?: ""
        val runningTime = intent.getIntExtra(EXTRA_RUNNING_TIME, 0)
        val summary = intent.getStringExtra(EXTRA_SUMMARY) ?: ""

        movieDetailPresenter = MovieDetailPresenterImpl(this, InMemoryMovieTicketRepository, title, screeningDate, MIN_RESERVATION_COUNT)
        movieDetailPresenter?.loadMovieDetails(posterImageId, title, screeningDate, runningTime, summary)

        initClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        movieDetailPresenter?.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        movieDetailPresenter?.restoreState(savedInstanceState)
        movieDetailPresenter?.updateReservationCountDisplay()
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
        findViewById<TextView>(R.id.screeningDate).text =
            getString(R.string.screening_date_format, screeningDate)
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, runningTime)
        findViewById<TextView>(R.id.summary).text = summary
    }

    override fun showReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }

    private fun initClickListener() {
        minusButton.setOnClickListener {
            movieDetailPresenter?.minusReservationCount()
        }

        plusButton.setOnClickListener {
            movieDetailPresenter?.plusReservationCount()
        }

        reserveButton.setOnClickListener {
            movieDetailPresenter?.requestReservationResult()
        }
    }

    override fun moveToReservationPage(reservationData: ReservationData) {
        val intent =
            Intent(this, ReservationResultActivity::class.java).apply {
                putExtra(EXTRA_TITLE, reservationData.movieTitle)
                putExtra(EXTRA_SCREENING_DATE, reservationData.screeningDate)
                putExtra(EXTRA_RESERVATION_COUNT, reservationData.reservationCount)
                putExtra(EXTRA_TOTAL_PRICE, reservationData.totalPrice)
            }
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val EXTRA_POSTER_IMAGE_SRC = "posterSrc"
        const val EXTRA_TITLE = "title"
        const val EXTRA_SCREENING_DATE = "screeningDate"
        const val EXTRA_RUNNING_TIME = "runningTime"
        const val EXTRA_SUMMARY = "summary"
        const val EXTRA_RESERVATION_COUNT = "reservationCount"
        const val EXTRA_TOTAL_PRICE = "totalPrice"
    }
}

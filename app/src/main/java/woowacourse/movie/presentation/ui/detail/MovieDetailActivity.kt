package woowacourse.movie.presentation.ui.detail

import android.content.Intent
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.dto.ReservationData
import woowacourse.movie.presentation.ui.reservation.ReservationResultActivity

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private var movieDetailPresenter: MovieDetailContract.Presenter? = null
    private lateinit var viewHolder: MovieDetailsViewHolder

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewHolder = MovieDetailsViewHolder(findViewById(android.R.id.content))

        val posterImageId = intent.getIntExtra(EXTRA_POSTER_IMAGE_SRC, 0)
        val title = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val screeningDate = intent.getStringExtra(EXTRA_SCREENING_DATE) ?: ""
        val runningTime = intent.getIntExtra(EXTRA_RUNNING_TIME, 0)
        val summary = intent.getStringExtra(EXTRA_SUMMARY) ?: ""

        movieDetailPresenter =
            MovieDetailPresenterImpl(this, MovieTicketRepositoryImpl, title, screeningDate)

        movieDetailPresenter?.loadMovieDetails(
            posterImageId,
            title,
            screeningDate,
            runningTime,
            summary,
        )

        initClickListener()
    }

    private fun initClickListener() {
        viewHolder.minusButton.setOnClickListener {
            movieDetailPresenter?.minusReservationCount()
        }
        viewHolder.plusButton.setOnClickListener {
            movieDetailPresenter?.plusReservationCount()
        }
        viewHolder.reserveButton.setOnClickListener {
            movieDetailPresenter?.requestReservationResult()
        }
    }

    override fun showMovieDetail(
        posterImageId: Int,
        title: String,
        screeningDate: String,
        runningTime: Int,
        summary: String,
    ) {
        viewHolder.bindDetails(posterImageId, title, screeningDate, runningTime, summary)
    }

    override fun showReservationCount(count: Int) {
        viewHolder.updateReservationCount(count)
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
        const val EXTRA_POSTER_IMAGE_SRC = "posterSrc"
        const val EXTRA_TITLE = "title"
        const val EXTRA_SCREENING_DATE = "screeningDate"
        const val EXTRA_RUNNING_TIME = "runningTime"
        const val EXTRA_SUMMARY = "summary"
        const val EXTRA_RESERVATION_COUNT = "reservationCount"
        const val EXTRA_TOTAL_PRICE = "totalPrice"
    }
}

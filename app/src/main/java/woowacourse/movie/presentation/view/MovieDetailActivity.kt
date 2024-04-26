package woowacourse.movie.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.presenter.MovieDetailPresenterImpl
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private lateinit var movieDetailPresenter: MovieDetailContract.Presenter
    private val reservationCountTextView: TextView by lazy {
        findViewById(R.id.reservationCount)
    }
    private val reserveButton: Button by lazy {
        findViewById(R.id.reserveButton)
    }

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(INTENT_MOVIE_ID, DEFAULT_MOVIE_ID)

        movieDetailPresenter = MovieDetailPresenterImpl(movieId)
        movieDetailPresenter.attachView(this)

        savedInstanceState?.let {
            val count = it.getInt(SIS_COUNT_KEY)
            movieDetailPresenter.initReservationCount(count)
        }

        setupReservationCountButton()
        setReserveButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailPresenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = reservationCountTextView.text.toString().toInt()
        outState.putInt(SIS_COUNT_KEY, count)
    }

    override fun showMovieDetail(movieUiModel: MovieUiModel) {
        findViewById<ImageView>(R.id.posterImage).setImageResource(movieUiModel.posterImageId)
        findViewById<TextView>(R.id.title).text = movieUiModel.title
        findViewById<TextView>(R.id.screeningDate).text =
            getString(
                R.string.screening_date_format,
                movieUiModel.screeningStartDate,
                movieUiModel.screeningEndDate,
                )
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, movieUiModel.runningTime)
        findViewById<TextView>(R.id.summary).text = movieUiModel.summary
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

    override fun moveToReservationResult(
        title: String,
        screeningStartDate: String,
        reservationCount: Int,
        totalPrice: Int,
    ) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(ReservationResultActivity.INTENT_TITLE, title)
        intent.putExtra(ReservationResultActivity.INTENT_SCREENING_DATE, screeningStartDate)
        intent.putExtra(ReservationResultActivity.INTENT_RESERVATION_COUNT, reservationCount)
        intent.putExtra(ReservationResultActivity.INTENT_TOTAL_PRICE, totalPrice)
        startActivity(intent)

    }

    private fun setReserveButton() {
        reserveButton.setOnClickListener {
            movieDetailPresenter.onReserveButtonClicked()
        }
    }

    companion object {
        val defaultPosterImageId = R.drawable.img_noimg
        const val DEFAULT_MOVIE_ID = -1
        const val INTENT_MOVIE_ID = "movieId"
        const val SIS_COUNT_KEY = "count"
    }
}

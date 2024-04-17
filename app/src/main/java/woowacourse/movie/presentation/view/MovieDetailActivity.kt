package woowacourse.movie.presentation.view

import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.presenter.MovieDetailPresenterImpl
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity

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

        val posterImageId = intent.getIntExtra("posterImageId", 0)
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val runningTime = intent.getIntExtra("runningTime", 0)
        val summary = intent.getStringExtra("summary") ?: ""

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
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
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
            intent.putExtra("title", movieDetailPresenter.movieTicket.movieTitle)
            intent.putExtra("screeningDate", movieDetailPresenter.movieTicket.screeningDate)
            intent.putExtra("reservationCount", movieDetailPresenter.movieTicket.count)
            intent.putExtra("totalPrice", movieDetailPresenter.movieTicket.totalPrice())
            startActivity(intent)
        }
    }
}

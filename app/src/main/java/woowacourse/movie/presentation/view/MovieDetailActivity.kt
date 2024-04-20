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
        
        movieDetailPresenter = MovieDetailPresenterImpl(this, title, screeningDate)
        
        showMovieDetail(posterImageId, title, screeningDate, runningTime, summary)
        setupReservationCountButton()
        moveToReservationResult()
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("title", movieDetailPresenter?.movieTicket?.movieTitle ?: "")
        outState.putString("screeningDate", movieDetailPresenter?.movieTicket?.screeningDate ?: "")
        outState.putInt(
            "reservationCount",
            movieDetailPresenter?.movieTicket?.reservationCount ?: 0
        )
    }
    
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val title = savedInstanceState.getString("title") ?: ""
        val screeningDate = savedInstanceState.getString("screeningDate") ?: ""
        val reservationCount = savedInstanceState.getInt("reservationCount", 0)
        reservationCountTextView.text = reservationCount.toString()
        movieDetailPresenter =
            MovieDetailPresenterImpl(this, title, screeningDate, reservationCount)
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
    
    private fun setupReservationCountButton() {
        minusButton.setOnClickListener {
            movieDetailPresenter?.minusReservationCount()
        }
        
        plusButton.setOnClickListener {
            movieDetailPresenter?.plusReservationCount()
        }
    }
    
    override fun showReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }
    
    override fun moveToReservationResult() {
        reserveButton.setOnClickListener {
            movieDetailPresenter?.let {
                val intent = Intent(this, ReservationResultActivity::class.java)
                intent.putExtra(EXTRA_TITLE, it.movieTicket.movieTitle)
                intent.putExtra(EXTRA_SCREENING_DATE, it.movieTicket.screeningDate)
                intent.putExtra(EXTRA_RESERVATION_COUNT, it.movieTicket.reservationCount)
                intent.putExtra(EXTRA_TOTAL_PRICE, it.movieTicket.totalPrice())
                startActivity(intent)
            }
        }
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

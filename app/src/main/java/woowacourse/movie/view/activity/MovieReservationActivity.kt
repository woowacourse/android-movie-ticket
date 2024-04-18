package woowacourse.movie.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.conrtract.MovieReservationContract
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.constants.MovieReservationKey
import woowacourse.movie.model.MovieContent
import woowacourse.movie.presenter.MovieReservationPresenter
import woowacourse.movie.view.ui.DateUi

class MovieReservationActivity :
    BaseActivity<MovieReservationContract.Presenter>(),
    MovieReservationContract.View {
    private val presenter: MovieReservationContract.Presenter by lazy { initializePresenter() }
    private val posterImage by lazy { findViewById<ImageView>(R.id.poster_image) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val runningTimeText by lazy { findViewById<TextView>(R.id.running_time_text) }
    private val synopsisText by lazy { findViewById<TextView>(R.id.synopsis_text) }
    private val minusButton by lazy { findViewById<Button>(R.id.minus_button) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val plusButton by lazy { findViewById<Button>(R.id.plus_button) }
    private val reservationButton by lazy { findViewById<Button>(R.id.reservation_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movieContentId = movieContentId()
        if (movieContentId == DEFAULT_VALUE) {
            handleError()
            return
        }

        setUpUi(movieContentId)
        setOnClickButtonListener()
    }

    override fun initializePresenter() = MovieReservationPresenter(this)

    private fun movieContentId() = intent.getLongExtra(MovieContentKey.ID, DEFAULT_VALUE)

    override fun handleError() {
        Log.e(TAG, "Invalid MovieContentKey")
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(movieContentId: Long) {
        presenter.setUpMovieContent(movieContentId)
        presenter.setUpReservationCount()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnClickButtonListener() {
        minusButton.setOnClickListener {
            presenter.clickMinusButton()
        }

        plusButton.setOnClickListener {
            presenter.clickPlusButton()
        }

        reservationButton.setOnClickListener {
            presenter.clickReservationButton()
        }
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text =
                DateUi.screeningDateMessage(screeningDate, this@MovieReservationActivity)
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }
    }

    override fun updateReservationCountUi(reservationCount: Int) {
        reservationCountText.text = reservationCount.toString()
    }

    override fun moveMovieReservationCompleteView(reservationCount: Int) {
        Intent(this, MovieReservationCompleteActivity::class.java).apply {
            putExtra(MovieContentKey.ID, movieContentId())
            putExtra(MovieReservationKey.COUNT, reservationCount)
            startActivity(this)
        }
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val DEFAULT_VALUE = -1L
    }
}

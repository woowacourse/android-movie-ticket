package woowacourse.movie.presentation.reservation.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity

class MovieReservationActivity : AppCompatActivity(), MovieReservationView {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var countView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_movie)
        val id = intent.getLongExtra(KEY_SCREEN_MOVIE_ID, INVALID_ID)
        if (id == INVALID_ID) {
            initErrorView()
            return
        }
        initSuccessView()
        initClickListener()
        initPresenter(id, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putInt(KEY_RESERVATION_COUNT, presenter.count())
        }
    }

    private fun initErrorView() {
        val errorLayout = findViewById<LinearLayout>(R.id.cl_reservation_movie_error)
        val successLayout = findViewById<ConstraintLayout>(R.id.cl_reservation_movie_success)
        errorLayout.visibility = ConstraintLayout.VISIBLE
        successLayout.visibility = ConstraintLayout.GONE
    }

    private fun initSuccessView() {
        countView = findViewById(R.id.tv_reservation_count)
        plusButton = findViewById(R.id.btn_reservation_plus)
        minusButton = findViewById(R.id.btn_reservation_minus)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initClickListener() {
        plusButton.setOnClickListener {
            presenter.plusCount()
        }
        minusButton.setOnClickListener {
            presenter.minusCount()
        }
        findViewById<Button>(R.id.btn_reservation_complete).setOnClickListener {
            presenter.completeReservation()
        }
    }

    private fun initPresenter(
        id: Long,
        savedInstanceState: Bundle?,
    ) {
        presenter =
            if (savedInstanceState == null) {
                MovieReservationPresenter(id, this, MovieRepositoryFactory.movieRepository())
            } else {
                val count = savedInstanceState.getInt(KEY_RESERVATION_COUNT)
                MovieReservationPresenter(id, this, MovieRepositoryFactory.movieRepository(), count)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieReservation(reservation: MovieReservationUiModel) {
        val (id, title, imageRes, screenDate, description, runningTime) = reservation
        findViewById<ImageView>(R.id.iv_reservation_poster).setImageResource(imageRes)
        findViewById<TextView>(R.id.tv_reservation_title).text = title
        findViewById<TextView>(R.id.tv_reservation_movie_description).text = description
        findViewById<TextView>(R.id.tv_reservation_running_date).text = screenDate
        findViewById<TextView>(R.id.tv_reservation_running_time).text = runningTime
    }

    override fun updateHeadCount(count: Int) {
        countView.text = count.toString()
    }

    override fun navigateToReservationResultView(reservationId: Long) {
        val intent = ReservationResultActivity.newIntent(this, reservationId)
        startActivity(intent)
    }

    companion object {
        const val INVALID_ID: Long = -1
        val KEY_SCREEN_MOVIE_ID: String? = this::class.java.canonicalName
        const val KEY_RESERVATION_COUNT: String = "KEY_RESERVATION_COUNT"

        @JvmStatic
        fun newIntent(
            context: Context,
            movieId: Long,
        ): Intent =
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(KEY_SCREEN_MOVIE_ID, movieId)
            }
    }
}

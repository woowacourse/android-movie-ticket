package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.model.MovieReservationUiModel
import woowacourse.movie.presenter.MovieReservationPresenter
import woowacourse.movie.view.MovieReservationView

class MovieReservationActivity : AppCompatActivity(), MovieReservationView {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var countView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        initView()
        initClickListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getLongExtra(EXTRA_SCREEN_MOVIE_ID, INVALID_SCREEN_MOVIE_ID)
        presenter =
            MovieReservationPresenter(
                id, this, StubMovieRepository,
            )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val count = findViewById<TextView>(R.id.tv_detail_count).text.toString().toInt()
        outState.putInt(STATE_COUNT_ID, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val counter = savedInstanceState.getInt(STATE_COUNT_ID)
        countView.text = counter.toString()
    }

    private fun initView() {
        countView = findViewById(R.id.tv_detail_count)
        plusButton = findViewById(R.id.btn_detail_plus)
        minusButton = findViewById(R.id.btn_detail_minus)
    }

    private fun initClickListener() {
        plusButton.setOnClickListener {
            presenter.plusCount()
        }
        minusButton.setOnClickListener {
            presenter.minusCount()
        }
        findViewById<Button>(R.id.btn_detail_complete).setOnClickListener {
            presenter.completeReservation()
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
        findViewById<ImageView>(R.id.iv_detail_poster).setImageResource(imageRes)
        findViewById<TextView>(R.id.tv_detail_title).text = title
        findViewById<TextView>(R.id.tv_detail_movie_desc).text = description
        findViewById<TextView>(R.id.tv_detail_running_date).text = screenDate
        findViewById<TextView>(R.id.tv_detail_running_time).text = runningTime
    }

    override fun updateHeadCount(count: Int) {
        countView.text = count.toString()
    }

    override fun navigateToReservationResultView(reservationId: Long) {
        startActivity(ReservationResultActivity.getIntent(this, reservationId))
    }

    companion object {
        const val EXTRA_SCREEN_MOVIE_ID: String = "screenMovieId"
        const val INVALID_SCREEN_MOVIE_ID = -1L
        private const val STATE_COUNT_ID = "count"

        fun getIntent(
            context: Context,
            reservationId: Long,
        ): Intent {
            return Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(EXTRA_SCREEN_MOVIE_ID, reservationId)
            }
        }
    }
}

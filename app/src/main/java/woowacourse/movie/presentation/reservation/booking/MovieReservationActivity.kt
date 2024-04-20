package woowacourse.movie.presentation.reservation.booking

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity

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
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(ReservationResultActivity.EXTRA_RESERVATION_ID, reservationId)
        startActivity(intent)
    }

    companion object {
        val EXTRA_SCREEN_MOVIE_ID: String? = this::class.java.canonicalName
        const val INVALID_SCREEN_MOVIE_ID = -1L
    }
}

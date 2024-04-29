package woowacourse.movie.presentation.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.common.ui.startActivity
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.screening.ScreeningMovieActivity

class ReservationResultActivity : AppCompatActivity(), ReservationResultView {
    private lateinit var presenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        initBackPressCallback()
        val id = intent.getLongExtra(KEY_RESERVATION_ID, INVALID_RESERVATION_ID)
        presenter =
            ReservationResultPresenter(
                repository = MovieRepositoryFactory.movieRepository(),
                view = this,
            ).apply { loadReservationResult(id) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showResult(reservationResult: ReservationResultUiModel) {
        val (title, cancelDeadLine, date, count, totalPrice, seat) = reservationResult
        findViewById<TextView>(R.id.tv_result_title).text = title
        findViewById<TextView>(R.id.tv_result_cancel_deadline).text =
            getString(R.string.reservation_cancel_deadline_format, cancelDeadLine)
        findViewById<TextView>(R.id.tv_result_running_date).text = date
        findViewById<TextView>(R.id.tv_result_count).text =
            getString(R.string.reservation_head_count_format, count, seat)
        findViewById<TextView>(R.id.tv_result_total_price).text =
            getString(R.string.reservation_total_price_format, totalPrice)
    }

    override fun showErrorView() {
        val errorLayout = findViewById<LinearLayout>(R.id.cl_reservation_result_error)
        val successLayout = findViewById<ConstraintLayout>(R.id.cl_reservation_result_success)
        errorLayout.visibility = ConstraintLayout.VISIBLE
        successLayout.visibility = ConstraintLayout.GONE
    }

    private fun initBackPressCallback() {
        onBackPressedDispatcher.addCallback {
            startActivity<ScreeningMovieActivity> {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        }
    }

    companion object {
        val KEY_RESERVATION_ID: String? = this::class.java.canonicalName
        const val INVALID_RESERVATION_ID: Long = -1

        @JvmStatic
        fun newIntent(
            context: Context,
            reservationId: Long,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(KEY_RESERVATION_ID, reservationId)
            }
    }
}

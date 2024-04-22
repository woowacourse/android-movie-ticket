package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.model.ReservationResultUiModel
import woowacourse.movie.presenter.ReservationResultPresenter
import woowacourse.movie.view.ReservationResultView

class ReservationResultActivity : AppCompatActivity(), ReservationResultView {
    private lateinit var presenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        val reservationId = intent.getLongExtra(EXTRA_RESERVATION_ID, INVALID_RESERVATION_ID)

        presenter =
            ReservationResultPresenter(
                repository = StubMovieRepository,
                view = this,
            )
        presenter.loadReservationResult(reservationId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showResult(reservationResult: ReservationResultUiModel) {
        val (title, cancelDeadLine, date, count, totalPrice) = reservationResult
        findViewById<TextView>(R.id.tv_result_title).text = title
        findViewById<TextView>(R.id.tv_result_cancel_deadline).text =
            getString(R.string.reservation_cancel_deadline_format, cancelDeadLine)
        findViewById<TextView>(R.id.tv_result_running_date).text = date
        findViewById<TextView>(R.id.tv_result_count).text =
            getString(R.string.reservation_head_count_format, count)
        findViewById<TextView>(R.id.tv_result_total_price).text =
            getString(R.string.reservation_total_price_format, totalPrice)
    }

    companion object {
        const val EXTRA_RESERVATION_ID: String = "reservationId"
        const val INVALID_RESERVATION_ID: Long = -1

        fun getIntent(
            context: Context,
            reservationId: Long,
        ): Intent {
            return Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(EXTRA_RESERVATION_ID, reservationId)
            }
        }
    }
}

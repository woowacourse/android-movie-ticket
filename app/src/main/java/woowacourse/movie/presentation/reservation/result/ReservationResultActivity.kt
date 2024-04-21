package woowacourse.movie.presentation.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository

class ReservationResultActivity : AppCompatActivity(), ReservationResultView {
    private lateinit var presenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        val id = intent.getLongExtra(KEY_RESERVATION_ID, INVALID_RESERVATION_ID)
        if (id == INVALID_RESERVATION_ID) {
            initErrorView()
            return
        }
        presenter =
            ReservationResultPresenter(
                id = id,
                repository = FakeMovieRepository,
                view = this,
            )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initErrorView() {
        val errorLayout = findViewById<LinearLayout>(R.id.cl_reservation_result_error)
        val successLayout = findViewById<ConstraintLayout>(R.id.cl_reservation_result_success)
        errorLayout.visibility = ConstraintLayout.VISIBLE
        successLayout.visibility = ConstraintLayout.GONE
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

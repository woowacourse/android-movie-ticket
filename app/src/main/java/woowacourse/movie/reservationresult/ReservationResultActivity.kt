package woowacourse.movie.reservationresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.reservationresult.uimodel.ReservationResultUiModel

class ReservationResultActivity : AppCompatActivity(), ReservationResultView {
    private lateinit var presenter: ReservationResultPresenter
    private lateinit var titleView: TextView
    private lateinit var cancelDeadLineView: TextView
    private lateinit var runningDateView: TextView
    private lateinit var countAndSeatView: TextView
    private lateinit var totalPriceView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        val reservationId = intent.getLongExtra(EXTRA_RESERVATION_ID, INVALID_RESERVATION_ID)

        initView()

        presenter =
            ReservationResultPresenter(
                repository = DummyMovies,
                view = this,
            )
        presenter.loadReservationResult(reservationId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        titleView = findViewById(R.id.tv_result_title)
        cancelDeadLineView = findViewById(R.id.tv_result_cancel_deadline)
        runningDateView = findViewById(R.id.tv_result_running_date)
        countAndSeatView = findViewById(R.id.tv_result_count)
        totalPriceView = findViewById(R.id.tv_result_total_price)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showResult(reservationResult: ReservationResultUiModel) {
        with(reservationResult) {
            titleView.text = title
            cancelDeadLineView.text =
                getString(R.string.reservation_cancel_deadline_format, cancelDeadLine)
            runningDateView.text = dateTime
            countAndSeatView.text =
                headCount.count + " | " + seats.joinToString { it.showPosition }
            totalPriceView.text =
                totalPrice.price
        }
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

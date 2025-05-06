package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.getParcelableCompat
import woowacourse.movie.view.movies.MoviesActivity
import woowacourse.movie.view.reservation.seat.SeatSelectionActivity
import woowacourse.movie.view.reservation.seat.SeatSelectionActivity.Companion.BUNDLE_KEY_RESERVATION_INFO

class ReservationResultActivity :
    BaseActivity(R.layout.activity_reservation_result),
    ReservationResultContract.View {
    private lateinit var presenter: ReservationResultPresenter

    private val tvMovieTitle by lazy { findViewById<TextView>(R.id.tv_movie_title) }
    private val tvMovieDate by lazy { findViewById<TextView>(R.id.tv_movie_date) }
    private val tvReservationCountInfo by lazy { findViewById<TextView>(R.id.tv_reservation_count_info) }
    private val tvReservationSeats by lazy { findViewById<TextView>(R.id.tv_reservation_seats) }
    private val tvCancelDescription by lazy { findViewById<TextView>(R.id.tv_cancel_description) }
    private val tvTotalPrice by lazy { findViewById<TextView>(R.id.tv_reservation_total_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ReservationResultPresenter(this, this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservationInfo = intent.getParcelableCompat<ReservationInfo>(BUNDLE_KEY_RESERVATION_INFO)

        presenter.loadReservationInfo(reservationInfo)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(this@ReservationResultActivity, MoviesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            },
        )

        setupCancelDescription()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MoviesActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showReservationResult(
        title: String,
        dateTime: String,
        count: String,
        seats: String,
        totalPrice: String,
    ) {
        tvMovieTitle?.text = title
        tvMovieDate.text = dateTime
        tvReservationCountInfo?.text = count
        tvReservationSeats.text = seats
        tvTotalPrice?.text = totalPrice
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    private fun setupCancelDescription() {
        tvCancelDescription?.text =
            getString(R.string.reservation_result_cancel_time_description, CANCELLATION_TIME)
    }

    companion object {
        private const val CANCELLATION_TIME = 15

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(
                    SeatSelectionActivity.BUNDLE_KEY_RESERVATION_INFO,
                    reservationInfo,
                )
            }
    }
}

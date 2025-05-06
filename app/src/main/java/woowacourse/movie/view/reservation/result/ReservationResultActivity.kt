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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultActivity :
    BaseActivity(R.layout.activity_reservation_result),
    ReservationResultContract.View {
    val presenter = ReservationResultPresenter(this)

    private val tvReservationSeats = findViewById<TextView>(R.id.tv_reservation_seats)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MoviesActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showReservationResult(reservationInfo: ReservationInfo) {
        displayReservationResult(reservationInfo)
    }

    private fun displayReservationResult(reservationInfo: ReservationInfo) {
        setupCancelDescription()
        setupMovieTitle(reservationInfo.title)
        setupMovieDate(reservationInfo.reservationDateTime)
        setupReservationCount(reservationInfo.reservationCount.value)
        setupTotalPrice(reservationInfo.totalPrice())

        setupSeats(reservationInfo.seats)
    }

    private fun setupCancelDescription() {
        val tvCancelDescription = findViewById<TextView>(R.id.tv_cancel_description)
        tvCancelDescription?.text =
            getString(R.string.reservation_result_cancel_time_description, CANCELLATION_TIME)
    }

    private fun setupMovieTitle(title: String?) {
        val tvMovieTitle = findViewById<TextView>(R.id.tv_movie_title)
        tvMovieTitle?.text = title
    }

    private fun setupMovieDate(reservationDateTime: LocalDateTime?) {
        val tvMovieDate = findViewById<TextView>(R.id.tv_movie_date)
        tvMovieDate.text =
            reservationDateTime?.format(
                DateTimeFormatter.ofPattern(
                    getString(R.string.reservation_datetime_format),
                ),
            )
    }

    private fun setupReservationCount(reservationCount: Int?) {
        val tvReservationCountInfo = findViewById<TextView>(R.id.tv_reservation_count_info)
        tvReservationCountInfo?.text =
            getString(R.string.reservation_count_info).format(reservationCount)
    }

    private fun setupTotalPrice(totalPrice: Int?) {
        val tvTotalPrice = findViewById<TextView>(R.id.tv_reservation_total_price)
        tvTotalPrice?.text =
            getString(R.string.reservation_total_price).format(totalPrice)
    }

    private fun setupSeats(seats: List<woowacourse.movie.domain.model.Seat>) {
        tvReservationSeats.text =
            getString(
                R.string.seat_split_line,
                seats.joinToString(",") { it.row.toString() + it.column.toString() },
            )
    }

    companion object {
        private const val CANCELLATION_TIME = 15
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(
                    BUNDLE_KEY_RESERVATION_INFO,
                    SeatSelectionActivity.BUNDLE_KEY_RESERVATION_INFO,
                    reservationInfo,
                )
            }
    }
}

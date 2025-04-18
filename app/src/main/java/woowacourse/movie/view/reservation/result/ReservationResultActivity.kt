package woowacourse.movie.view.reservation.result

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.toDateTimeFormatter
import woowacourse.movie.view.movies.MoviesActivity
import java.time.LocalDateTime

class ReservationResultActivity : BaseActivity(R.layout.activity_reservation_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun setupViews(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        displayReservationResult()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MoviesActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun displayReservationResult() {
        val reservationInfo =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getSerializableExtra(
                    BUNDLE_KEY_RESERVATION_INFO,
                    ReservationInfo::class.java,
                )
            } else {
                intent?.getSerializableExtra(BUNDLE_KEY_RESERVATION_INFO) as? ReservationInfo
            }
        setupCancelDescription()
        setupMovieTitle(reservationInfo?.title)
        setupMovieDate(reservationInfo?.reservationDateTime)
        setupReservationNumber(reservationInfo?.reservationNumber)
        setupTotalPrice(reservationInfo?.totalPrice())
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
        RESERVATION_DATETIME_FORMAT.toDateTimeFormatter()?.let { formatter ->
            tvMovieDate?.text = reservationDateTime?.format(formatter)
        }
    }

    private fun setupReservationNumber(reservationNumber: Int?) {
        val tvReservationNumberInfo = findViewById<TextView>(R.id.tv_reservation_number_info)
        tvReservationNumberInfo?.text =
            getString(R.string.reservation_number_info).format(reservationNumber)
    }

    private fun setupTotalPrice(totalPrice: Int?) {
        val tvTotalPrice = findViewById<TextView>(R.id.tv_reservation_total_price)
        tvTotalPrice?.text =
            getString(R.string.reservation_total_price).format(totalPrice)
    }

    companion object {
        private const val CANCELLATION_TIME = 15
        private const val RESERVATION_DATETIME_FORMAT = "yyyy.M.d HH:mm"
        const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"
    }
}

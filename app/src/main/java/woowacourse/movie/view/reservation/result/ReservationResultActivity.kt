package woowacourse.movie.view.reservation.result

import android.content.Intent
import android.view.MenuItem
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.movies.MoviesActivity

class ReservationResultActivity : BaseActivity(R.layout.activity_reservation_result) {
    override fun setupViews() {
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
            intent?.getParcelableExtra<ReservationInfo>(getString(R.string.bundle_key_reservation_info))
        val title = reservationInfo?.title
        val reservationDateTime = reservationInfo?.reservationDateTime

        val tvCancelDescription = findViewById<TextView>(R.id.tv_cancel_description)
        tvCancelDescription?.let {
            it.text =
                getString(R.string.reservation_result_cancel_time_description, CANCELLATION_TIME)
        }

        val tvMovieTitle = findViewById<TextView>(R.id.tv_movie_title)
        tvMovieTitle?.let { it.text = title }

        val tvMovieDate = findViewById<TextView>(R.id.tv_movie_date)
        tvMovieDate?.let { it.text = reservationDateTime }

        val tvReservationNumberInfo = findViewById<TextView>(R.id.tv_reservation_number_info)
        tvReservationNumberInfo?.let {
            it.text =
                getString(R.string.reservation_number_info).format(reservationInfo?.reservationNumber)
        }

        val tvTotalPrice = findViewById<TextView>(R.id.tv_reservation_total_price)
        tvTotalPrice?.let {
            it.text =
                getString(R.string.reservation_total_price).format(
                    reservationInfo?.totalPrice(),
                )
        }
    }

    companion object {
        private const val CANCELLATION_TIME = 15
    }
}

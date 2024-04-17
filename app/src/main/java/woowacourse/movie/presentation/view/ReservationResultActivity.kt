package woowacourse.movie.presentation.view

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity

class ReservationResultActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_reservation_result

    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setUpFromIntent()
    }

    private fun setUpFromIntent() {
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val reservationCount = intent.getIntExtra("reservationCount", 0)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.reservationCount).text =
            this.getString(R.string.reservation_count_format, reservationCount)
        findViewById<TextView>(R.id.totalPrice).text =
            this.getString(R.string.reservation_total_price_format, totalPrice)
    }
}

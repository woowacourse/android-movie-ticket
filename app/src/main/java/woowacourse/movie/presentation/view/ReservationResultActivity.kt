package woowacourse.movie.presentation.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity

class ReservationResultActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_reservation_result

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setUpFromIntent()
    }

    private fun setUpFromIntent() {
        val title = intent.getStringExtra(INTENT_TITLE) ?: ""
        val screeningDate = intent.getStringExtra(INTENT_SCREENING_DATE) ?: ""
        val reservationCount = intent.getIntExtra(INTENT_RESERVATION_COUNT, 0)
        val totalPrice = intent.getIntExtra(INTENT_TOTAL_PRICE, 0)

        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.reservationCount).text =
            this.getString(R.string.reservation_count_format, reservationCount)
        findViewById<TextView>(R.id.totalPrice).text =
            this.getString(R.string.reservation_total_price_format, totalPrice)
    }

    companion object {
        const val INTENT_TITLE = "title"
        const val INTENT_SCREENING_DATE = "screeningDate"
        const val INTENT_RESERVATION_COUNT = "reservationCount"
        const val INTENT_TOTAL_PRICE = "totalPrice"
    }
}

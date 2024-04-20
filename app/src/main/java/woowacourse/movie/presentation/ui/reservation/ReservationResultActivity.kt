package woowacourse.movie.presentation.ui.reservation

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity

class ReservationResultActivity : BaseActivity(), ReservationResultContract.View {
    private lateinit var presenter: ReservationResultPresenter
    
    override fun getLayoutResId(): Int = R.layout.activity_reservation_result
    
    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        presenter = ReservationResultPresenter(this, intent)
        presenter.loadReservationDetails()
    }
    
    override fun showTitle(title: String) {
        findViewById<TextView>(R.id.title).text = title
    }
    
    override fun showScreeningDate(screeningDate: String) {
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
    }
    
    override fun showReservationCount(reservationCount: Int) {
        findViewById<TextView>(R.id.reservationCount).text =
            getString(R.string.reservation_count_format, reservationCount)
    }
    
    override fun showTotalPrice(totalPrice: Int) {
        findViewById<TextView>(R.id.totalPrice).text =
            getString(R.string.reservation_total_price_format, totalPrice)
    }
    
    override fun showError(error: String) {
        showSnackBar(error)
    }
}

package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.presenter.PurchaseConfirmationPresenter
import woowacourse.movie.uiModels.reservation.ReservationBrief

class PurchaseConfirmationActivity : AppCompatActivity(), PurchaseConfirmationContract.View {
    private val reservationBriefViews: ReservationBriefViews by lazy {
        ReservationBriefViews(
            findViewById<TextView>(R.id.movie_title_confirmation),
            findViewById<TextView>(R.id.purchase_confirmation_screening_datetime),
            findViewById<TextView>(R.id.ticket_position),
            findViewById<TextView>(R.id.ticket_charge),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val presenter = PurchaseConfirmationPresenter(this)
        presenter.loadReservation()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun displayReservation(reservationBrief: ReservationBrief) {
        reservationBriefViews.set(reservationBrief)
    }
}

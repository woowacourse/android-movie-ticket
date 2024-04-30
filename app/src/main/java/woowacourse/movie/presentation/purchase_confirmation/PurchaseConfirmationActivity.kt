package woowacourse.movie.presentation.purchase_confirmation

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.uimodel.reservation.ReservationBrief

class PurchaseConfirmationActivity : AppCompatActivity(), PurchaseConfirmationContract.View {
    private val reservationBriefViewHolder: ReservationBriefViewHolder by lazy {
        ReservationBriefViewHolder(
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
        reservationBriefViewHolder.set(reservationBrief)
    }
}

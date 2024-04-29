package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.presenter.PurchaseConfirmationPresenter
import woowacourse.movie.ui.ReservationBrief

class PurchaseConfirmationActivity : AppCompatActivity(), PurchaseConfirmationContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val presenter = PurchaseConfirmationPresenter(this)
        val reservationId = intent.getIntExtra("ReservationId", -1)
        presenter.loadReservation(reservationId)
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
        findViewById<TextView>(R.id.movie_title_confirmation).text = reservationBrief.movieTitle
        findViewById<TextView>(R.id.purchase_confirmation_screening_datetime).text = reservationBrief.screeningDateTime
        findViewById<TextView>(R.id.ticket_position).text = reservationBrief.positions.joinToString(",")
        findViewById<TextView>(R.id.ticket_charge).text = reservationBrief.price
    }
}

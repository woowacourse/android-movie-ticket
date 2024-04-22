package woowacourse.movie.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.presenter.PurchaseConfirmationPresenter

class PurchaseConfirmationActivity : AppCompatActivity(), PurchaseConfirmationContract.View {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val presenter = PurchaseConfirmationPresenter(intent, this)
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

    override fun displayReservation(reservation: Reservation) {
        val movie = reservation.screening.movie
        findViewById<TextView>(R.id.movie_title_confirmation).text = movie.title.content
        findViewById<TextView>(R.id.purchase_movie_running_time).text =
            movie.runningTime.time.toString() + "분"
        findViewById<TextView>(R.id.ticket_charge).text = PRICE.format(reservation.getCharge())
    }

    companion object {
        const val PRICE = "price: %d"
    }
}

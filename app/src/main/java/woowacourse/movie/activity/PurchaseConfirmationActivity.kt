package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.uimodel.format
import woowacourse.movie.model.Reservation
import woowacourse.movie.presenter.PurchaseConfirmationPresenter

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

    override fun displayReservation(reservation: Reservation) {
        //TODO
        /*
        val movie = reservation.movieBrief
        val movieDetail = movie.movieDetail
        findViewById<TextView>(R.id.movie_title_confirmation).text = movieDetail.title.format()
        findViewById<TextView>(R.id.purchase_movie_running_time).text = movieDetail.runningTime.format()
        findViewById<TextView>(R.id.ticket_charge).text = reservation.getCharge().toString()

         */
    }
}

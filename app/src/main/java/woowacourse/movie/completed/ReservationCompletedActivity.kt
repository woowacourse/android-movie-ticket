package woowacourse.movie.completed

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Reservation
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.onStart()
    }

    override fun readTicketData(): Reservation? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("reservation", Reservation::class.java)
        } else {
            intent.getSerializableExtra("reservation") as? Reservation
        }

    override fun initializeTicketDetails(reservation: Reservation) {
        findViewById<TextView>(R.id.movie_title).text = reservation.getTitle()
        findViewById<TextView>(R.id.opening_day).text = reservation.getScreeningTime()
        findViewById<TextView>(R.id.quantity).text = "일반 ${reservation.getQuantity()}명"
        findViewById<TextView>(R.id.price).text =
            "${DecimalFormat("#,###").format(reservation.price)}원 (현장 결제)"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

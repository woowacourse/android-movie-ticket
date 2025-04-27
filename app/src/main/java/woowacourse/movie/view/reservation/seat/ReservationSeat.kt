package woowacourse.movie.view.reservation.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.dialog.DialogFactory
import woowacourse.movie.view.reservation.detail.ReservationActivity

class ReservationSeat : AppCompatActivity(), ReservationSeatContract.View {
    private val presenter: ReservationSeatContract.Present by lazy {
        ReservationSeatPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(KEY_TICKET, Ticket::class.java)
            } else {
                intent.getSerializableExtra(KEY_TICKET) as? Ticket
            }
        presenter.fetchData(ticket)
    }

    override fun handleInvalidTicket() {
        DialogFactory().showError(this) {
            ReservationActivity.returnToReserve(this)
        }
    }

    override fun showReservationSeatScreen() {
        // TODO("Not yet implemented")
    }

    companion object {
        private const val KEY_TICKET = "ticket"

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, ReservationSeat::class.java).putExtra(
                KEY_TICKET,
                ticket,
            )
    }
}

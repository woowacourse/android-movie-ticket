package woowacourse.movie.view.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.parcelableExtraCompat
import woowacourse.movie.view.reservation.MovieReservationCompleteActivity
import woowacourse.movie.view.reservation.ticket.TicketUiModel

class SeatSelectActivity :
    AppCompatActivity(),
    SeatSelectContract.View {
    private lateinit var presenter: SeatSelectPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()

        val ticket =
            intent.parcelableExtraCompat(EXTRA_TICKET, TicketUiModel::class.java)
                ?: finish().run { return }
        presenter = SeatSelectPresenter(this, ticket)

        initView()
        initConfirmButton()
    }

    override fun navigateToCompleteScreen(ticket: TicketUiModel) {
        val intent = MovieReservationCompleteActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun setView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initView() {}

    private fun initConfirmButton() {
        val alertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(R.string.confirm_reservation)
                .setMessage(R.string.confirm_reservation_message)
                .setPositiveButton(R.string.complete_reservation) { _, _ -> presenter.completeReservation() }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)

        val confirmButton: Button = findViewById(R.id.confirm_button)
        confirmButton.setOnClickListener {
            // TODO: 선택된 좌석이 있는지 확인
            if (true) {
                alertDialog.show()
            } else {
                Toast.makeText(this, R.string.select_seat, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent =
            Intent(context, SeatSelectActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
    }
}

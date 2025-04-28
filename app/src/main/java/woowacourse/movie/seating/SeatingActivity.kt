package woowacourse.movie.seating

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.completedbooking.CompletedBookingActivity
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.parcelableCompat

class SeatingActivity : AppCompatActivity(), SeatingContract.View {
    private lateinit var seatingPresenter: SeatingPresenter

    private val tableLayout: TableLayout by lazy { findViewById(R.id.table_layout_seating) }
    private val titleTextView: TextView by lazy { findViewById(R.id.seating_movie_title) }
    private val priceTextView: TextView by lazy { findViewById(R.id.seating_total_price) }
    private val confirmTextView: TextView by lazy { findViewById(R.id.seating_confirm) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seating)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seating_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        seatingPresenter = SeatingPresenter(this)

        val reservationInfo: ReservationInfo =
            intent.parcelableCompat(KEY_SEATING, ReservationInfo::class.java)
        seatingPresenter.set(reservationInfo)

        val selectedColor = ContextCompat.getColor(this, R.color.seat_selected)
        val defaultColor = Color.WHITE

        tableLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEach { seatView ->
                seatView.setOnClickListener {
                    val seatName = seatView.text.toString()
                    val currentColor = (seatView.background as? ColorDrawable)?.color
                    val isSelected = (currentColor == selectedColor)

                    if (isSelected) {
                        seatView.setBackgroundColor(defaultColor)
                        seatingPresenter.clickedSeat(seatName)
                    } else {
                        if (seatingPresenter.canSelectMoreSeat()) {
                            seatView.setBackgroundColor(selectedColor)
                            seatingPresenter.clickedSeat(seatName)
                        }
                    }
                }
            }
    }

    override fun showTitle(title: String) {
        titleTextView.text = title
    }

    override fun showPrice(price: String) {
        priceTextView.text = getString(R.string.movieTotalPrice, price)
    }

    override fun showActivateButton(ticket: Ticket) {
        val color = ContextCompat.getColor(this, R.color.seat_activate_reservation_button)
        confirmTextView.setBackgroundColor(color)
        confirmTextView.setOnClickListener {
            showDialog(ticket)
        }
    }

    override fun showDeactivateButton() {
        val color = ContextCompat.getColor(this, R.color.seat_deactivate_reservation_button)
        confirmTextView.setBackgroundColor(color)
    }

    override fun showActivateSeat() {
        tableLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEach { seatView ->
                seatView.isEnabled = true
            }
    }

    override fun showDeactivateSeat(selectedSeats: MutableSet<String>) {
        tableLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEach { seatView ->
                val seatName = seatView.text.toString()
                seatView.isEnabled = selectedSeats.contains(seatName)
            }
    }

    private fun showDialog(ticket: Ticket) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_reservation_title))
            .setMessage(getString(R.string.dialog_reservation_message))
            .setPositiveButton(getString(R.string.dialog_reservation_positive_text)) { _, _ ->
                startActivity(CompletedBookingActivity.newIntent(this, ticket))
            }
            .setNegativeButton(getString(R.string.dialog_reservation_negative_text)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    companion object {
        private const val KEY_SEATING = "SEATING"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent {
            return Intent(context, SeatingActivity::class.java).putExtra(
                KEY_SEATING,
                reservationInfo,
            )
        }
    }
}

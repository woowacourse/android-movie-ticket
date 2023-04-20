package woowacourse.movie.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.ui.getParcelable
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PriceModel
import woowacourse.movie.ui.model.mapToMovieTicket
import woowacourse.movie.ui.model.mapToMovieTicketModel
import woowacourse.movie.ui.model.mapToPriceModel
import woowacourse.movie.ui.model.seat.SeatModel
import woowacourse.movie.ui.model.seat.SeatsModel
import woowacourse.movie.ui.model.seat.mapToSeat
import java.text.DecimalFormat

class SeatPickerActivity : AppCompatActivity() {
    private var count = 0
    private val seats = SeatsModel().getAll()
    private val ticket: MovieTicket by lazy { mapToMovieTicket(intent.getParcelable("ticket")!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val seatViews = findViewById<TableLayout>(R.id.layout_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        intent.getParcelable<MovieTicketModel>("ticket")?.let {
            setSeatViews(seatViews, it)
            findViewById<TextView>(R.id.seat_picker_title).text = it.title
            findViewById<TextView>(R.id.seat_picker_price).text = it.price.format()
        }

        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        doneButton.setOnClickListener {
            showReservationCheckDialog()
        }
        doneButton.isClickable = ticket.peopleCount.count == count
    }

    private fun PriceModel.format(): String =
        getString(R.string.price, DecimalFormat("#,###").format(amount))

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSeatViews(seatViews: List<TextView>, ticket: MovieTicketModel) {
        seatViews.zip(seats) { view, seat ->
            view.text = getString(R.string.seat, seat.row.letter, seat.column.value)
            view.setTextColor(getColor(seat.rank.color))
            view.setOnClickListener {
                selectSeat(view, ticket, seat)
                changeDoneButtonState(ticket)
            }
        }
    }

    private fun selectSeat(
        view: TextView,
        ticketModel: MovieTicketModel,
        seat: SeatModel
    ) {
        if (view.isSelected) {
            removeSeat(view, seat)
            count--
        } else if (count < ticketModel.peopleCount.count) {
            addSeat(view, seat)
            count++
        } else {
            Toast
                .makeText(this, getString(R.string.toast_message_seat_selection_done), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun removeSeat(
        view: TextView,
        seat: SeatModel
    ) {
        updateEmptySeatView(view)
        ticket.cancelSeat(mapToSeat(seat))
        findViewById<TextView>(R.id.seat_picker_price).text =
            mapToPriceModel(ticket.getDiscountPrice()).format()
    }

    private fun updateEmptySeatView(view: TextView) {
        view.setBackgroundColor(getColor(R.color.white))
        view.isSelected = false
    }

    private fun addSeat(
        view: TextView,
        seat: SeatModel
    ) {
        updateFullSeatView(view)
        ticket.reserveSeat(mapToSeat(seat))
        findViewById<TextView>(R.id.seat_picker_price).text =
            mapToPriceModel(ticket.getDiscountPrice()).format()
    }

    private fun updateFullSeatView(view: TextView) {
        view.setBackgroundColor(getColor(R.color.seat_selected))
        view.isSelected = true
    }

    private fun changeDoneButtonState(ticket: MovieTicketModel) {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        if (count == ticket.peopleCount.count) {
            doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_on))
            doneButton.isClickable = true
        } else {
            doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_off))
            doneButton.isClickable = false
        }
    }

    private fun showReservationCheckDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title_seat_selection_check))
            .setMessage(getString(R.string.dialog_message_seat_selection_check))
            .setPositiveButton(getString(R.string.dialog_positive_button_seat_selection_check)) { _, _ ->
                moveToTicketActivity()
            }
            .setNegativeButton(getString(R.string.dialog_negative_button_seat_selection_check)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun moveToTicketActivity() {
        val intent = Intent(this, MovieTicketActivity::class.java)
        intent.putExtra("ticket", mapToMovieTicketModel(ticket))
        startActivity(intent)
    }
}

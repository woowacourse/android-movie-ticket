package woowacourse.movie.ui.activity

import android.content.Context
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
import woowacourse.movie.ui.getParcelableByKey
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PriceModel
import woowacourse.movie.ui.model.mapToMovieTicket
import woowacourse.movie.ui.model.mapToMovieTicketModel
import woowacourse.movie.ui.model.mapToMovieTicketModelWithOriginalPrice
import woowacourse.movie.ui.model.mapToPriceModel
import woowacourse.movie.ui.model.seat.SeatModel
import woowacourse.movie.ui.model.seat.SeatsModel
import woowacourse.movie.ui.model.seat.mapToSeat

class SeatPickerActivity : AppCompatActivity() {
    private val seats = SeatsModel().getAll()
    private lateinit var ticket: MovieTicket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadSavedData(savedInstanceState)

        val ticketModel = ticket.mapToMovieTicketModel()
        setSeatViews(ticketModel)
        setTicketViews(ticketModel)
        setDoneButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKET_INSTANCE_KEY, ticket.mapToMovieTicketModelWithOriginalPrice())
    }

    private fun PriceModel.format(): String = getString(R.string.price, amount)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSeatViews(ticketModel: MovieTicketModel) {
        val seatViews = findViewById<TableLayout>(R.id.layout_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        seatViews.zip(seats) { view, seat ->
            view.text = getString(R.string.seat, seat.row.letter, seat.column.value)
            view.setTextColor(getColor(seat.rank.color))
            if (ticketModel.isSelectedSeat(seat)) {
                view.isSelected = true
                view.setBackgroundColor(getColor(R.color.seat_selected))
            }
            view.setOnClickListener {
                selectSeat(view, seat)
                updateDoneButtonState()
            }
        }
    }

    private fun selectSeat(
        view: TextView,
        seat: SeatModel
    ) {
        if (view.isSelected) {
            removeSeat(view, seat)
            return
        }
        selectEmptySeat(view, seat)
    }

    private fun removeSeat(
        view: TextView,
        seat: SeatModel
    ) {
        updateEmptySeatView(view)
        ticket.cancelSeat(seat.mapToSeat())
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        priceView.text = ticket.getDiscountPrice().mapToPriceModel().format()
    }

    private fun updateEmptySeatView(view: TextView) {
        view.setBackgroundColor(getColor(R.color.white))
        view.isSelected = false
    }

    private fun selectEmptySeat(
        view: TextView,
        seat: SeatModel
    ) {
        if (ticket.canReserveSeat()) {
            addSeat(view, seat)
            return
        }
        notifyUnableToAddSeat()
    }

    private fun addSeat(
        view: TextView,
        seat: SeatModel
    ) {
        updateFullSeatView(view)
        ticket.reserveSeat(seat.mapToSeat())
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        priceView.text = ticket.getDiscountPrice().mapToPriceModel().format()
    }

    private fun updateFullSeatView(view: TextView) {
        view.setBackgroundColor(getColor(R.color.seat_selected))
        view.isSelected = true
    }

    private fun notifyUnableToAddSeat() {
        Toast
            .makeText(
                this,
                getString(R.string.toast_message_seat_selection_done),
                Toast.LENGTH_SHORT
            )
            .show()
    }

    private fun updateDoneButtonState() {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        if (ticket.canReserveSeat()) {
            deactivateDoneButton(doneButton)
        } else {
            activateDoneButton(doneButton)
        }
    }

    private fun activateDoneButton(doneButton: TextView) {
        doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_on))
        doneButton.isClickable = true
    }

    private fun deactivateDoneButton(doneButton: TextView) {
        doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_off))
        doneButton.isClickable = false
    }

    private fun setTicketViews(ticketModel: MovieTicketModel) {
        val titleView = findViewById<TextView>(R.id.seat_picker_title)
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        titleView.text = ticketModel.title
        priceView.text = ticketModel.price.format()
    }

    private fun setDoneButton() {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        doneButton.setOnClickListener {
            showReservationCheckDialog()
        }
        updateDoneButtonState()
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
        val intent = MovieTicketActivity.createIntent(this, ticket.mapToMovieTicketModel())
        startActivity(intent)
    }

    private fun loadSavedData(savedInstanceState: Bundle?) {
        val ticketModel = savedInstanceState?.getParcelableByKey<MovieTicketModel>(TICKET_INSTANCE_KEY)
            ?: intent.getParcelable(TICKET_EXTRA_KEY)!!
        ticket = ticketModel.mapToMovieTicket()
    }

    companion object {
        private const val TICKET_EXTRA_KEY = "ticket_extra"
        private const val TICKET_INSTANCE_KEY = "ticket_instance"

        fun createIntent(context: Context, ticket: MovieTicketModel): Intent {
            val intent = Intent(context, SeatPickerActivity::class.java)
            intent.putExtra(TICKET_EXTRA_KEY, ticket)
            return intent
        }
    }
}

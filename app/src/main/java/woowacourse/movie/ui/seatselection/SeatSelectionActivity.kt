package woowacourse.movie.ui.seatselection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.woowacourse.movie.domain.policy.DiscountDecorator
import woowacourse.movie.R
import woowacourse.movie.extensions.exitForUnNormalCase
import woowacourse.movie.extensions.getParcelableCompat
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.mapper.toTicket
import woowacourse.movie.model.mapper.toTickets
import woowacourse.movie.model.mapper.toTicketsUI
import woowacourse.movie.model.seat.SeatPositionUI
import woowacourse.movie.ui.ticketing.TicketingActivity
import woowacourse.movie.ui.ticketingresult.TicketingResultActivity

class SeatSelectionActivity : AppCompatActivity() {
    private lateinit var reservation: ReservationUI
    private lateinit var ticketsUI: TicketsUI

    private var moviePrice = DEFAULT_MOVIE_PRICE

    private val ticketCountUI: TicketCountUI? by lazy {
        intent.getParcelableCompat(TicketingActivity.TICKET_COUNT_KEY)
    }

    private val seatTableLayout: TableLayout by lazy {
        findViewById(R.id.table_seat)
    }
    private val seatTable: SeatTable by lazy {
        SeatTable(seatTableLayout, ticketCountUI!!, ::setButtonEnable, ::setTicket)
    }

    private val okButton: Button by lazy {
        findViewById(R.id.btn_ok)
    }

    private val textPrice: TextView by lazy {
        findViewById(R.id.tv_price)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reservation = intent.getParcelableCompat(TicketingActivity.RESERVATION_KEY)
            ?: return exitForUnNormalCase(MESSAGE_EMPTY_RESERVATION)

        initMovieData()
        initButtonClickListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableCompat<TicketsUI>(TICKETS_STATE_KEY)?.run {
            setRestoreTickets(this)
        }
    }

    private fun setRestoreTickets(ticketsUI: TicketsUI) {
        val tickets = ticketsUI.toTickets()
        ticketsUI.tickets.forEach {
            seatTable.setSeatPosition(it.seatPosition)
            tickets.addTicket(it.toTicket())
        }
        this.ticketsUI = tickets.toTicketsUI()
    }

    private fun initMovieData() {
        ticketsUI = reservation.ticketsUI
        seatTable
        findViewById<TextView>(R.id.tv_title).text = reservation.movie.title
        setMoviePrice(moviePrice)
    }

    private fun setMoviePrice(price: Int) {
        findViewById<TextView>(R.id.tv_price).text = getString(R.string.movie_price, price)
    }

    private fun initButtonClickListener() {
        okButton.setOnClickListener {
            initDialog()
        }
    }

    private fun initDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_reservation_title))
            .setMessage(getString(R.string.dialog_reservation_message))
            .setNegativeButton(getString(R.string.dialog_reservation_negative_button)) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(
                getString(R.string.dialog_reservation_positive_button)
            ) { dialog, _ ->
                reserveMovie()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun reserveMovie() {
        reservation = ReservationUI(
            reservation.movie,
            reservation.dateTime,
            ticketsUI
        )

        val intent = TicketingResultActivity.getIntent(this@SeatSelectionActivity, reservation)
        startActivity(intent)
        finish()
    }

    private fun setButtonEnable(enabled: Boolean) {
        okButton.isEnabled = enabled
    }

    private fun setTicket(seatPosition: SeatPositionUI) {
        val tickets = ticketsUI.toTickets()
        val targetTicket = TicketUI(seatPosition).toTicket()
        if (tickets.find(targetTicket) != null) {
            tickets.removeTicket(targetTicket)
        } else {
            tickets.addTicket(targetTicket)
        }
        ticketsUI = tickets.toTicketsUI()
        changeTicketPrice()
    }

    private fun changeTicketPrice() {
        val price = calculateTicketPrice()
        textPrice.text = getString(R.string.movie_price, price)
    }

    private fun calculateTicketPrice(): Int {
        val tickets = ticketsUI.toTickets()
        if (tickets.isEmpty()) {
            return DEFAULT_MOVIE_PRICE
        }
        val decorator = DiscountDecorator(reservation.dateTime)
        return tickets.calculatePrice(decorator)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKETS_STATE_KEY, ticketsUI)
    }

    companion object {
        private const val TICKETS_STATE_KEY = "tickets_state_key"
        private const val MESSAGE_EMPTY_RESERVATION = "예매 정보가 없습니다"
        private const val DEFAULT_MOVIE_PRICE = 0

        internal fun getIntent(context: Context, reservationUI: ReservationUI, ticketCountUI: TicketCountUI): Intent {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(TicketingActivity.RESERVATION_KEY, reservationUI)
            intent.putExtra(TicketingActivity.TICKET_COUNT_KEY, ticketCountUI)
            return intent
        }
    }
}

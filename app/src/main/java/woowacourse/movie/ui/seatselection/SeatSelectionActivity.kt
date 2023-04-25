package woowacourse.movie.ui.seatselection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.woowacourse.movie.domain.policy.DiscountDecorator
import woowacourse.movie.R
import woowacourse.movie.extensions.exitForUnNormalCase
import woowacourse.movie.extensions.getParcelableCompat
import woowacourse.movie.extensions.showDialog
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

    private val ticketCountUI: TicketCountUI? by lazy {
        intent.getParcelableCompat(TicketingActivity.TICKET_COUNT_KEY)
    }

    private val seatTableLayout: TableLayout by lazy {
        findViewById(R.id.table_seat)
    }
    private val seatTable: SeatTable by lazy {
        SeatTable(seatTableLayout, ticketCountUI!!, ::onEnabledChange, ::onTicketCreated)
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
        ticketsUI.tickets.forEach {
            seatTable.setSeatPosition(it.seatPosition)
        }
        this.ticketsUI = ticketsUI
    }

    private fun initMovieData() {
        ticketsUI = reservation.ticketsUI
        seatTable
        findViewById<TextView>(R.id.tv_title).text = reservation.movie.title
        changeTicketPrice()
    }

    private fun initButtonClickListener() {
        okButton.setOnClickListener { initDialog() }
    }

    private fun initDialog() {
        showDialog(
            getString(R.string.dialog_reservation_title),
            getString(R.string.dialog_reservation_message),
            getString(R.string.dialog_reservation_negative_button),
            getString(R.string.dialog_reservation_positive_button),
            {},
            { reserveMovie() }
        )
    }

    private fun reserveMovie() {
        val intent = TicketingResultActivity.getIntent(
            this@SeatSelectionActivity,
            ReservationUI(
                reservation.movie,
                reservation.dateTime,
                ticketsUI
            )
        )
        startActivity(intent)
        finish()
    }

    private fun onEnabledChange(enabled: Boolean) {
        okButton.isEnabled = enabled
    }

    private fun onTicketCreated(seatPosition: SeatPositionUI) {
        val tickets = ticketsUI.toTickets()
        val targetTicket = TicketUI(seatPosition).toTicket()
        tickets.addOrRemoveTicket(targetTicket)
        ticketsUI = tickets.toTicketsUI()
        changeTicketPrice()
    }

    private fun changeTicketPrice() {
        val price = calculateTicketPrice()
        textPrice.text = getString(R.string.movie_price, price)
    }

    private fun calculateTicketPrice(): Int {
        val tickets = ticketsUI.toTickets()
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

        internal fun getIntent(
            context: Context,
            reservationUI: ReservationUI,
            ticketCountUI: TicketCountUI
        ): Intent {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(TicketingActivity.RESERVATION_KEY, reservationUI)
            intent.putExtra(TicketingActivity.TICKET_COUNT_KEY, ticketCountUI)
            return intent
        }
    }
}

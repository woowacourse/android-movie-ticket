package woowacourse.movie.ui.seatselection

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
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.mapper.toTickets
import woowacourse.movie.model.seat.SeatPositionUI
import woowacourse.movie.ui.ticketing.TicketingActivity
import woowacourse.movie.ui.ticketingresult.TicketingResultActivity

class SeatSelectionActivity : AppCompatActivity() {
    private lateinit var reservation: ReservationUI
    private lateinit var tickets: TicketsUI
    private var moviePrice = DEFAULT_MOVIE_PRICE

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
        initSeatTable()
        initButtonClickListener()
    }

    private fun initMovieData() {
        findViewById<TextView>(R.id.tv_title).text = reservation.movie.title
        setMoviePrice(moviePrice)
    }

    private fun setMoviePrice(price: Int) {
        findViewById<TextView>(R.id.tv_price).text = getString(R.string.movie_price, price)
    }

    private fun initSeatTable() {
        val seatTableLayout = findViewById<TableLayout>(R.id.table_seat)
        SeatTable(seatTableLayout, reservation, ::setButtonEnable, ::setTicketPrice)
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
        val intent = Intent(this@SeatSelectionActivity, TicketingResultActivity::class.java)
        intent.putExtra(TICKETS_KEY, tickets)
        startActivity(intent)
        finish()
    }

    private fun setButtonEnable(enabled: Boolean) {
        okButton.isEnabled = enabled
    }

    private fun setTicketPrice(seatPositions: List<SeatPositionUI>) {
        val price = calculateTicketPrice(seatPositions)
        textPrice.text = getString(R.string.movie_price, price)
    }

    private fun calculateTicketPrice(seatPositions: List<SeatPositionUI>): Int {
        if (seatPositions.isEmpty()) {
            return DEFAULT_MOVIE_PRICE
        }
        tickets = TicketsUI(seatPositions.map { TicketUI(it) }, reservation)
        val decorator = DiscountDecorator(reservation.dateTime)
        return tickets.toTickets().calculatePrice(decorator)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MESSAGE_EMPTY_RESERVATION = "예매 정보가 없습니다"

        private const val DEFAULT_MOVIE_PRICE = 0

        internal const val TICKETS_KEY = "tickets"
    }
}

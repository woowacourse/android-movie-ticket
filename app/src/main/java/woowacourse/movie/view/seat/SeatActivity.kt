package woowacourse.movie.view.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.complete.BookingCompleteActivity
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.seat.manager.SeatView
import kotlin.lazy

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private lateinit var presenter: SeatContract.Presenter
    private lateinit var seatView: SeatView
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seatTable) }
    private val priceText by lazy { findViewById<TextView>(R.id.tv_price) }
    private val bookingBtn by lazy { findViewById<TextView>(R.id.btn_booking) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)
        val booking = intent.getSerializable(KEY_BOOKING, Booking::class.java)

        presenter = SeatPresenter(this, Seats(), booking)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initSeat()
        initBookingBtn()
    }

    private fun initSeat() {
        seatView =
            SeatView(seatTable) { coordination ->
                presenter.changeSeat(coordination)
            }
        seatView.initSeats()
    }

    private fun initBookingBtn() {
        bookingBtn.setOnClickListener {
            showDialog()
        }
    }

    override fun showBookingInformation(title: String) {
        findViewById<TextView>(R.id.tv_title).text = title
    }

    override fun showSeat(seats: Set<Seat>) {
        seatView.updateSeats(seats)
    }

    override fun showToast(peopleCount: Int) {
        val msg = getString(R.string.text_over_limit_people_count).format(peopleCount)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showPrice(price: Int) {
        val formattedPrice = StringFormatter.thousandFormat(price)
        priceText.text = getString(R.string.text_korea_unit).format(formattedPrice)
    }

    override fun setConfirmButtonEnabled(enabled: Boolean) {
        bookingBtn.isEnabled = enabled
    }

    override fun moveToBookingComplete(ticket: Ticket) {
        val intent = BookingCompleteActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.text_booking_dialog_title)
            .setMessage(R.string.text_booking_dialog_description)
            .setPositiveButton(R.string.text_booking_dialog_positive_button) { _, _ ->
                presenter.attemptConfirmBooking()
            }
            .setNegativeButton(R.string.text_booking_dialog_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val KEY_BOOKING = "BOOKING"

        fun newIntent(
            context: Context,
            booking: Booking,
        ) = Intent(context, SeatActivity::class.java).apply {
            putExtra(KEY_BOOKING, booking)
        }
    }
}

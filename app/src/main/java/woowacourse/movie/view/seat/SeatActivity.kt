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
import woowacourse.movie.presenter.seat.SeatContract
import woowacourse.movie.presenter.seat.SeatPresenter
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.complete.BookingCompleteActivity
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.seat.manager.SeatView

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private val presenter: SeatContract.Presenter by lazy { SeatPresenter(this, Seats()) }
    private lateinit var seatView: SeatView

    private val seatTable by lazy { findViewById<TableLayout>(R.id.seatTable) }
    private val priceText by lazy { findViewById<TextView>(R.id.tv_price) }
    private val bookingBtn by lazy { findViewById<TextView>(R.id.btn_booking) }

    private lateinit var defaultBooking: Booking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)
        defaultBooking = intent.getSerializable(KEY_BOOKING, Booking::class.java)

        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initMovieTitle()
        initSeat()
        initBookingBtn()
        showPrice(0)
    }

    private fun initMovieTitle() {
        findViewById<TextView>(R.id.tv_title).text = defaultBooking.title
    }

    private fun initSeat() {
        seatView =
            SeatView(seatTable) { coordination ->
                presenter.changeSeat(coordination, defaultBooking.count.value)
            }
        seatView.initSeats()
    }

    private fun initBookingBtn() {
        bookingBtn.setOnClickListener {
            showDialog()
        }
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

    override fun moveToBookingComplete(
        seats: Set<Seat>,
        price: Int,
    ) {
        val intent =
            BookingCompleteActivity.newIntent(
                this,
                defaultBooking.title,
                defaultBooking.bookingDate,
                defaultBooking.bookingTime,
                defaultBooking.count.value,
                price,
                seatToLabel(seats),
            )
        startActivity(intent)
    }

    private fun seatToLabel(seats: Set<Seat>): String {
        return seats.joinToString {
            val rowLetter = ('A' + it.x - 1)
            val columnNumber = it.y
            "$rowLetter$columnNumber"
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.text_booking_dialog_title)
            .setMessage(R.string.text_booking_dialog_description)
            .setPositiveButton(R.string.text_booking_dialog_positive_button) { _, _ ->
                presenter.attemptConfirmBooking(defaultBooking.count.value)
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

package woowacourse.movie.booking.seat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.StringFormatter
import woowacourse.movie.booking.complete.BookingCompleteActivity
import woowacourse.movie.booking.complete.BookingCompleteUiModel
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.SeatType

class BookingSeatActivity :
    AppCompatActivity(),
    BookingSeatContract.View {
    private lateinit var presenter: BookingSeatContract.Presenter
    private lateinit var ticketInfo: BookingCompleteUiModel
    private lateinit var seatLayout: TableLayout
    private lateinit var totalPrice: TextView
    private lateinit var confirmButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

        presenter = BookingSeatPresenter(this)

        seatLayout = findViewById<TableLayout>(R.id.booking_seat_table)
        seatLayout.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEachIndexed { colIndex, textView ->
                        val seat = createSeat(rowIndex, colIndex)
                        textView.tag = seat
                        setupSeatColor(textView, seat)

                        textView.setOnClickListener {
                            presenter.toggleSeatSelection(seat = seat)
                        }
                    }
            }

        setupConfirmButton()
    }

    private fun setupConfirmButton() {
        confirmButton.setOnClickListener {
            presenter.onConfirmClicked()
        }
    }

    private fun createSeat(
        rowIndex: Int,
        colIndex: Int,
    ): Seat {
        val seatType =
            when (rowIndex) {
                0, 1 -> SeatType.B
                2, 3 -> SeatType.S
                4 -> SeatType.A
                else -> throw IllegalArgumentException("Invalid row index: $rowIndex")
            }
        return Seat(row = rowIndex + 1, col = colIndex + 1, rank = seatType)
    }

    private fun setupSeatColor(
        textView: TextView,
        seat: Seat,
    ) {
        textView.setTextColor(seat.rank.color.toColorInt())
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        seatLayout = findViewById(R.id.booking_seat_table)
        totalPrice = findViewById(R.id.booking_seat_price_textview)
        confirmButton = findViewById(R.id.booking_seat_confirm_textview)
    }

    override fun showMovieTitle(title: String) {
        findViewById<TextView>(R.id.booking_seat_title_textview).text = title
    }

    override fun updateSeatState(
        seat: Seat,
        isSelected: Boolean,
    ) {
        val textView = findSeatTextView(seat)
        if (isSelected) {
            textView.setBackgroundColor(Color.YELLOW)
        } else {
            textView.setBackgroundColor(Color.WHITE)
        }
    }

    private fun findSeatTextView(seat: Seat): TextView {
        val tableRow = seatLayout.getChildAt(seat.row - 1) as TableRow
        return tableRow.getChildAt(seat.col - 1) as TextView
    }

    override fun updateTotalPrice(price: Int) {
        totalPrice.text = StringFormatter.formatMoney(price)
    }

    override fun setConfirmEnabled(enabled: Boolean) {
        confirmButton.isEnabled = enabled

        if (enabled) {
            confirmButton.setBackgroundColor(Color.BLUE)
        }
    }

    override fun showConfirmDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.booking_detail_booking_check))
            .setMessage(getString(R.string.booking_detail_booking_check_description))
            .setPositiveButton(getString(R.string.booking_detail_booking_complete)) { _, _ ->
                // 화면 이동
            }.setNegativeButton(getString(R.string.booking_detail_booking_cancel), null)
            .setCancelable(false)
            .show()
    }

    override fun navigateToBookingComplete(bookingCompleteUiModel: BookingCompleteUiModel) {
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra("bookingComplete", bookingCompleteUiModel)
            }
        startActivity(intent)
        finish()
    }
}

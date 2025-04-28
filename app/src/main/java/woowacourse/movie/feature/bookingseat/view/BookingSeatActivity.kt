package woowacourse.movie.feature.bookingseat.view

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.feature.bookingcomplete.view.BookingCompleteActivity
import woowacourse.movie.feature.bookingseat.contract.BookingSeatContract
import woowacourse.movie.feature.bookingseat.presenter.BookingSeatPresenter
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieSeatUiModel
import woowacourse.movie.feature.model.SeatSelectionUiState
import woowacourse.movie.feature.model.SeatTypeUiModel
import woowacourse.movie.util.getExtra

class BookingSeatActivity :
    AppCompatActivity(),
    BookingSeatContract.View {
    private val presenter: BookingSeatContract.Presenter by lazy { BookingSeatPresenter(this) }
    private val seats: MutableMap<TextView, MovieSeatUiModel> = mutableMapOf()
    private val priceView: TextView by lazy { findViewById<TextView>(R.id.tv_booking_seat_movie_price) }
    private val seatSelectionCompleteView: TextView by lazy { findViewById<TextView>(R.id.tv_booking_seat_select_complete) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupSeatSelectCompleteClickListener()
        presenter.prepareBookingInfo(bookingInfo = intent.getExtra(BOOKING_INFO_KEY) ?: BookingInfoUiModel())
    }

    override fun showSeats(
        rowCount: Int,
        columnCount: Int,
    ) {
        val tableLayout = findViewById<TableLayout>(R.id.tl_booking_seat)

        for (rowIndex in 0 until rowCount) {
            val tableRow =
                TableRow(this).apply {
                    gravity = Gravity.CENTER
                }

            for (columnIndex in 0 until columnCount) {
                val movieSeat = presenter.prepareSeats(rowIndex + SEAT_POSITION_OFFSET, columnIndex + SEAT_POSITION_OFFSET)
                val seatView = createSeatTextView(movieSeat.toLabel())

                tableRow.addView(seatView)
                seats[seatView] = movieSeat
                setupSeatView(seatView, movieSeat)
            }

            tableLayout.addView(tableRow)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) presenter.cancelSeatSelection()
        return super.onOptionsItemSelected(item)
    }

    override fun showBookingInfo(bookingInfo: BookingInfoUiModel) {
        findViewById<TextView>(R.id.tv_booking_seat_movie_title).text = bookingInfo.movie.title
        priceView.text = getString(R.string.booking_seat_price, bookingInfo.totalPrice)
    }

    override fun showBookingCompleteDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.booking_detail_booking_check))
            .setMessage(getString(R.string.booking_detail_booking_check_description))
            .setPositiveButton(getString(R.string.booking_detail_booking_complete)) { _, _ ->
                presenter.confirmSeatSelection()
            }.setNegativeButton(getString(R.string.booking_detail_booking_cancel), null)
            .setCancelable(false)
            .show()
    }

    override fun updatePrice(price: Int) {
        priceView.text = getString(R.string.booking_seat_price, price)
    }

    override fun updateSeatSelectionCompleteButton(enabled: Boolean) {
        seatSelectionCompleteView.isEnabled = enabled
    }

    override fun navigateToBookingComplete(bookingInfo: BookingInfoUiModel) {
        val intent = BookingCompleteActivity.newIntent(this, bookingInfo)
        startActivity(intent)
        finish()
    }

    override fun navigateToBack() {
        finish()
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun createSeatTextView(seatName: String): TextView =
        TextView(this).apply {
            text = seatName
            width = 74.dpToPx()
            height = 80.dpToPx()
            gravity = Gravity.CENTER
            textSize = 22f
            setTypeface(typeface, Typeface.BOLD)
            background = ContextCompat.getDrawable(context, R.drawable.selector_movie_seat_background)
        }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()

    private fun setupSeatView(
        seatView: TextView,
        movieSeat: MovieSeatUiModel,
    ) {
        seatView.text = movieSeat.toLabel()
        seatView.isSelected = movieSeat.isSelected
        seatView.setTextColor(getSeatLabelColor(movieSeat.seatType))
        seatView.setOnClickListener {
            handleSeatSelection(seatView)
        }
    }

    private fun getSeatLabelColor(seatType: SeatTypeUiModel): Int =
        getColor(
            when (seatType) {
                SeatTypeUiModel.RANK_S -> R.color.green_300
                SeatTypeUiModel.RANK_A -> R.color.blue_300
                SeatTypeUiModel.RANK_B -> R.color.purple_400
                else -> 0
            },
        )

    private fun handleSeatSelection(button: TextView) {
        val seatSelectionUiState = presenter.selectSeat(seats[button] ?: return)

        when (seatSelectionUiState) {
            is SeatSelectionUiState.Success -> {
                seatSelectionUiState.selectedSeat.apply {
                    button.isSelected = isSelected
                    seats[button] = this
                }
            }

            is SeatSelectionUiState.ExceedCountFailure -> {
                Snackbar.make(button, getString(R.string.booking_seat_exceed_count_failure), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSeatSelectCompleteClickListener() {
        seatSelectionCompleteView.setOnClickListener {
            presenter.completeSeatSelection()
        }
    }

    companion object {
        private const val BOOKING_INFO_KEY = "BOOKING_INFO"
        private const val SEAT_POSITION_OFFSET = 1

        fun newIntent(
            context: Context,
            bookingInfo: BookingInfoUiModel,
        ): Intent =
            Intent(context, BookingSeatActivity::class.java).apply {
                putExtra(BOOKING_INFO_KEY, bookingInfo)
            }
    }
}

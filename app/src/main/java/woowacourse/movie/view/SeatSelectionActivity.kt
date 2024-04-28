package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.setPadding
import woowacourse.movie.R
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.presenter.SeatSelectionPresenter
import woowacourse.movie.presenter.contract.SeatSelectionContract
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_COUNT
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_DATE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_MOVIE_TITLE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_SCREENING_ID
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_TIME
import java.time.format.DateTimeFormatter

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val button: Button by lazy { findViewById(R.id.btn_complete_reservation) }
    private val seats: TableLayout by lazy { findViewById(R.id.tl_seats) }
    private val seatItems: List<TextView> by lazy {
        seats.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()
    }
    private val price: TextView by lazy { findViewById(R.id.tv_total_price) }
    private lateinit var rows: List<TableRow>
    private lateinit var presenter: SeatSelectionPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val screeningId = intent.getLongExtra(EXTRA_SCREENING_ID, -1)
        val count = intent.getIntExtra(EXTRA_COUNT, 0)
        val title = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        val date = intent.getStringExtra(EXTRA_DATE)
        val time = intent.getStringExtra(EXTRA_TIME)

        initializePresenter(savedInstanceState, screeningId, count, date, time, title)
        initializeReservationButton(screeningId, count)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_SELECTED_SEATS, presenter.selectedSeats.toTypedArray())
    }

    override fun initializeSeatTable(
        theaterSize: TheaterSize,
        rowClassInfo: Map<Int, SeatClass>,
        movieTitle: String,
        totalPrice: Int,
        selectedSeats: List<BookingSeat>,
    ) {
        findViewById<TextView>(R.id.tv_movie_title).text = movieTitle
        findViewById<TextView>(R.id.tv_total_price).text =
            getString(R.string.text_total_price, totalPrice)
        seats.isStretchAllColumns = true
        initializeRows(theaterSize, rowClassInfo, selectedSeats)
    }

    override fun toggleSeat(
        row: Int,
        column: Int,
        seatClass: SeatClass,
        isSelected: Boolean,
        columnSize: Int,
    ) {
        if (isSelected) {
            seatItems[row * columnSize + column].selectSeat()
        } else {
            seatItems[row * columnSize + column].cancelSeat()
        }
    }

    override fun updateTotalPrice(totalPrice: Int) {
        price.text = getString(R.string.text_total_price, totalPrice)
    }

    override fun updateButtonStatus(isAvailable: Boolean) {
        if (isAvailable) {
            button.setBackgroundColor(
                ContextCompat.getColor(
                    this@SeatSelectionActivity,
                    R.color.purple_500,
                ),
            )
            button.isClickable = true
        } else {
            button.setBackgroundColor(
                ContextCompat.getColor(
                    this@SeatSelectionActivity,
                    R.color.gray,
                ),
            )
            button.isClickable = false
        }
    }

    override fun navigateToResultScreen(
        movieId: Long,
        count: Int,
        seats: List<BookingSeat>,
        totalPrice: Int,
    ) {
        val shownSeats =
            seats.map {
                getString(
                    R.string.text_seat_position,
                    convertRowNumberIntoChar(it.row),
                    it.column + 1,
                )
            }
        val shownDate =
            presenter.dateTime.date.format(DateTimeFormatter.ofPattern(getString(R.string.format_datetime)))
        Intent(this, TicketingResultActivity::class.java).also {
            it.putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_NUM_TICKET, count)
                .putExtra(EXTRA_SEATS, shownSeats.toTypedArray())
                .putExtra(EXTRA_PRICE, totalPrice)
                .putExtra(EXTRA_DATE, shownDate)
                .putExtra(EXTRA_TIME, presenter.dateTime.time.toString())
            startActivity(it)
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(
            this@SeatSelectionActivity,
            message,
            Toast.LENGTH_SHORT,
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initializePresenter(
        savedInstanceState: Bundle?,
        screeningId: Long,
        count: Int,
        date: String?,
        time: String?,
        title: String?,
    ) {
        presenter = SeatSelectionPresenter(this)
        savedInstanceState?.let {
            val selectedSeats = it.getParcelableArray(KEY_SELECTED_SEATS, BookingSeat::class.java)
            selectedSeats?.let {
                presenter.loadSeats(
                    screeningId = screeningId,
                    numOfTickets = count,
                    date = date,
                    time = time,
                    title = title,
                    seats = selectedSeats.toList(),
                )
            }
        } ?: presenter.loadSeats(screeningId, count, date, time, title, emptyList())
    }

    private fun initializeReservationButton(
        screeningId: Long,
        count: Int,
    ) {
        button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_reservation_title))
                .setMessage(getString(R.string.dialog_reservation_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_positive_button)) { _, _ ->
                    presenter.makeReservation(screeningId, count)
                }
                .setNegativeButton(getString(R.string.dialog_negative_button)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun initializeRows(
        theaterSize: TheaterSize,
        rowClassInfo: Map<Int, SeatClass>,
        selectedSeats: List<BookingSeat>,
    ) {
        rows = List(theaterSize.rows) { TableRow(this) }
        rows.forEachIndexed { rowIndex, tableRow ->
            val seatClass = rowClassInfo[rowIndex + 1]
            tableRow.initializeSingleRow(seatClass, theaterSize, rowIndex, selectedSeats)
            seats.addView(tableRow)
        }
    }

    private fun convertRowNumberIntoChar(rowIndex: Int) = (START_ROW_CHAR.code + rowIndex).toChar()

    private fun TableRow.initializeSingleRow(
        seatClass: SeatClass?,
        theaterSize: TheaterSize,
        rowIndex: Int,
        selectedSeats: List<BookingSeat>,
    ) {
        seatClass?.let { grade ->
            repeat(theaterSize.columns) { columnIndex ->
                val seatItem = TextView(this@SeatSelectionActivity)
                seatItem.initializeSeatItem(
                    rowIndex = rowIndex,
                    columnIndex = columnIndex,
                    seatClass = grade,
                    selectedSeats = selectedSeats,
                    theaterSize = theaterSize,
                )
                addView(seatItem)
            }
        }
    }

    private fun TextView.initializeSeatItem(
        rowIndex: Int,
        columnIndex: Int,
        seatClass: SeatClass,
        selectedSeats: List<BookingSeat>,
        theaterSize: TheaterSize,
    ) {
        setSeatStyle(rowIndex, columnIndex, seatClass, selectedSeats)
        setOnClickListener {
            presenter.updateSeat(
                row = rowIndex,
                column = columnIndex,
                seatClass = seatClass,
                columnSize = theaterSize.columns,
            )
        }
    }

    private fun TextView.setSeatStyle(
        rowIndex: Int,
        columnIndex: Int,
        seatClass: SeatClass,
        selectedSeats: List<BookingSeat>,
    ) {
        val rowChar = convertRowNumberIntoChar(rowIndex)
        text =
            this@SeatSelectionActivity.getString(
                R.string.text_seat_position,
                rowChar,
                columnIndex + 1,
            )
        gravity = Gravity.CENTER

        val textColor =
            when (seatClass) {
                SeatClass.S -> R.color.green
                SeatClass.A -> R.color.purple_500
                SeatClass.B -> R.color.blue
            }
        setTextSize(COMPLEX_UNIT_SP, 22f)
        setTextColor(ContextCompat.getColor(this@SeatSelectionActivity, textColor))
        setPadding(40 * resources.displayMetrics.density.toInt())
        if (BookingSeat(rowIndex, columnIndex, seatClass) in selectedSeats) {
            setBackgroundColor(
                getColor(R.color.yellow),
            )
        }
    }

    private fun TextView.selectSeat() {
        setBackgroundColor(
            ContextCompat.getColor(
                this@SeatSelectionActivity,
                R.color.yellow,
            ),
        )
    }

    private fun TextView.cancelSeat() {
        setBackgroundColor(
            ContextCompat.getColor(
                this@SeatSelectionActivity,
                R.color.white,
            ),
        )
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_NUM_TICKET = "num_of_tickets"
        const val EXTRA_SEATS = "seats"
        const val EXTRA_PRICE = "price"
        private const val KEY_SELECTED_SEATS = "selected_seats"
        private const val START_ROW_CHAR = 'A'
    }
}

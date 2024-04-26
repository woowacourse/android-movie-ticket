package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.core.view.setPadding
import woowacourse.movie.R
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize
import woowacourse.movie.presenter.SeatSelectionPresenter
import woowacourse.movie.presenter.contract.SeatSelectionContract
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_COUNT
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_DATE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_MOVIE_TITLE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_SCREENING_ID
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_TIME

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val button: Button by lazy { findViewById(R.id.btn_complete_reservation) }
    private val seats: TableLayout by lazy { findViewById(R.id.tl_seats) }
    private val price: TextView by lazy { findViewById(R.id.tv_total_price) }
    private lateinit var rows: List<TableRow>
    private lateinit var presenter: SeatSelectionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val screeningId = intent.getLongExtra(EXTRA_SCREENING_ID, -1)
        val count = intent.getIntExtra(EXTRA_COUNT, 0)
        val title = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        val date = intent.getStringExtra(EXTRA_DATE)
        val time = intent.getStringExtra(EXTRA_TIME)

        presenter = SeatSelectionPresenter(this)
        presenter.initializeSeats(screeningId, count, date, time, title)

        button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예") { _, _ ->
                    presenter.makeReservation(screeningId, count)
                }
                .setNegativeButton("아니요") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun initializeSeatTable(
        theaterSize: TheaterSize,
        rowClassInfo: Map<Int, SeatClass>,
        movieTitle: String,
        totalPrice: Int,
    ) {
        findViewById<TextView>(R.id.tv_movie_title).text = movieTitle
        findViewById<TextView>(R.id.tv_total_price).text =
            getString(R.string.text_total_price, totalPrice)
        seats.isStretchAllColumns = true
        initializeRows(theaterSize, rowClassInfo)
    }

    private fun TextView.setSeatStyle(
        rowIndex: Int,
        columnIndex: Int,
        seatClass: SeatClass,
    ) {
        val rowChar = (START_ROW_CHAR.code + rowIndex).toChar()
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
    }

    private fun initializeRows(
        theaterSize: TheaterSize,
        rowClassInfo: Map<Int, SeatClass>,
    ) {
        rows = List(theaterSize.rows) { TableRow(this) }
        rows.forEachIndexed { rowIndex, tableRow ->
            val seatClass = rowClassInfo[rowIndex + 1]
            seatClass?.let { grade ->
                repeat(theaterSize.columns) { columnIndex ->
                    val seatItem = TextView(this)
                    seatItem.apply {
                        setSeatStyle(rowIndex, columnIndex, seatClass)
                        setOnClickListener {
                            presenter.updateSeat(
                                rowIndex,
                                columnIndex,
                                seatClass,
                                theaterSize.columns,
                            )
                        }
                    }
                    tableRow.addView(seatItem)
                }
            }
            seats.addView(tableRow)
        }
    }

    override fun toggleSeat(
        row: Int,
        column: Int,
        seatClass: SeatClass,
        isSelected: Boolean,
        columnSize: Int,
    ) {
        val seatItems =
            seats
                .children
                .filterIsInstance<TableRow>()
                .flatMap { it.children }
                .filterIsInstance<TextView>()
                .toList()

        if (isSelected) {
            seatItems[row * columnSize + column].selectSeat()
        } else {
            seatItems[row * columnSize + column].cancelSeat()
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
        seats: Array<String>,
        totalPrice: Int,
    ) {
        Intent(this, TicketingResultActivity::class.java).also {
            it.putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_NUM_TICKET, count)
                .putExtra(EXTRA_SEATS, seats)
                .putExtra(EXTRA_PRICE, totalPrice)
                .putExtra(EXTRA_DATE, presenter.dateTime.date)
                .putExtra(EXTRA_TIME, presenter.dateTime.time)
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

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_NUM_TICKET = "num_of_tickets"
        const val EXTRA_SEATS = "seats"
        const val EXTRA_PRICE = "price"
        private const val START_ROW_CHAR = 'A'
    }
}

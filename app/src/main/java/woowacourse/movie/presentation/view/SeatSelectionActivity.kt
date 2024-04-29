package woowacourse.movie.presentation.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.SeatSelectionContract
import woowacourse.movie.presentation.presenter.SeatSelectionPresenterImpl
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel

class SeatSelectionActivity : BaseActivity(), SeatSelectionContract.View {
    private lateinit var seatSelectionPresenter: SeatSelectionContract.Presenter
    private val seatingChartLayout: TableLayout by lazy {
        findViewById(R.id.seatingChartLayout)
    }
    private val movieTitle: TextView by lazy {
        findViewById(R.id.movieTitle)
    }
    private val totalPriceText: TextView by lazy {
        findViewById(R.id.totalPrice)
    }
    private val confirmButton: Button by lazy {
        findViewById(R.id.confirmButton)
    }

    override fun getLayoutResId(): Int = R.layout.activity_seat_selection

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        movieTitle.text = intent.getStringExtra(INTENT_TITLE)
        val reservationCount = intent.getIntExtra(INTENT_RESERVATION_COUNT, DEFAULT_COUNT)

        seatSelectionPresenter = SeatSelectionPresenterImpl(reservationCount)
        seatSelectionPresenter.attachView(this)

        confirmButton.isEnabled = false
        confirmButton.setOnClickListener {
            showConfirmationDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        seatSelectionPresenter.detachView()
    }

    override fun showSeatingChart(
        rowCount: Int,
        colCount: Int,
        seatRankInfo: List<IntRange>,
    ) {
        drawEachRowSeats(rowCount, colCount, seatRankInfo)
    }

    private fun drawEachRowSeats(
        rowCount: Int,
        colCount: Int,
        seatRankInfo: List<IntRange>,
    ) {
        repeat(rowCount) { row ->
            val rowSeats =
                TableRow(this).apply {
                    layoutParams =
                        TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT,
                        )
                }
            rowSeats.setPadding(10, 10, 10, 10)
            val color = getColorByRank(seatRankInfo, row)
            drawEachColumnSeats(rowSeats, row, colCount, color)
            seatingChartLayout.addView(rowSeats)
        }
    }

    private fun getColorByRank(
        seatRankInfo: List<IntRange>,
        row: Int,
    ): Int {
        return when (row) {
            in seatRankInfo[0] -> B_RANK_COLOR
            in seatRankInfo[1] -> S_RANK_COLOR
            in seatRankInfo[2] -> A_RANK_COLOR
            else -> Color.BLACK
        }
    }

    private fun drawEachColumnSeats(
        rowSeats: TableRow,
        row: Int,
        colCount: Int,
        color: Int,
    ) {
        repeat(colCount) { col ->
            val seatView =
                TextView(this).apply {
                    layoutParams =
                        TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                        )
                    gravity = Gravity.CENTER
                    setPadding(15, 30, 15, 30)
                    textSize = 18f
                    text =
                        String.format(
                            SEAT_POSITION_TEXT_FORMAT,
                            SEAT_ROW_START_VALUE + row,
                            SEAT_COL_START_VALUE + col,
                        )
                    setSeatClickListener(this, row, col)
                }
            seatView.setBackgroundColor(WHITE_COLOR)
            seatView.setTextColor(color)
            rowSeats.addView(seatView)
        }
    }

    private fun setSeatClickListener(
        seatView: TextView,
        row: Int,
        col: Int,
    ) {
        seatView.setOnClickListener {
            seatSelectionPresenter.selectSeat(row, col)
        }
    }

    override fun changeSeatColor(
        row: Int,
        col: Int,
    ) {
        val seatsRow = seatingChartLayout.getChildAt(row) as TableRow
        val seatView = seatsRow.getChildAt(col) as TextView
        val currentColor = (seatView.background as? ColorDrawable)?.color
        val newColor =
            when (currentColor) {
                WHITE_COLOR -> YELLOW_COLOR
                YELLOW_COLOR -> WHITE_COLOR
                else -> WHITE_COLOR
            }
        seatView.setBackgroundColor(newColor)
    }

    override fun updateTotalPrice(price: Int) {
        totalPriceText.text = this.getString(R.string.seat_total_price_format, price)
    }

    override fun changeConfirmClickable(hasMatchedCount: Boolean) {
        confirmButton.isEnabled = hasMatchedCount
    }

    override fun showAlreadyFilledSeatsSelectionMessage() {
        viewToastMessage(FILLED_SEATS_COUNT_MESSAGE, SHORT_DURATION)
    }

    override fun moveToReservationResult(movieTicketUiModel: MovieTicketUiModel) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(ReservationResultActivity.INTENT_TICKET, movieTicketUiModel)
        startActivity(intent)
    }

    private fun viewToastMessage(
        message: String?,
        duration: Int,
    ) {
        runOnUiThread {
            Toast.makeText(this, message, duration).show()
        }
    }

    private fun showConfirmationDialog() {
        val dialog =
            Dialog(this).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.reservation_confirm_dialog)
                setCancelable(false)
            }

        val cancelButton = dialog.findViewById<TextView>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        val confirmButton = dialog.findViewById<TextView>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            seatSelectionPresenter.onAcceptButtonClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    companion object {
        const val INTENT_TITLE = "title"
        const val INTENT_RESERVATION_COUNT = "reservationCount"
        const val DEFAULT_COUNT = 1
        const val SEAT_POSITION_TEXT_FORMAT = "%c%d"
        const val SEAT_ROW_START_VALUE = 'A'
        const val SEAT_COL_START_VALUE = 1
        const val FILLED_SEATS_COUNT_MESSAGE = "예매할 좌석 개수를 모두 선택했습니다."
        const val SHORT_DURATION = 5
        val B_RANK_COLOR = Color.argb(255, 142, 19, 236)
        val A_RANK_COLOR = Color.argb(255, 25, 211, 88)
        val S_RANK_COLOR = Color.argb(255, 27, 72, 233)
        const val WHITE_COLOR = Color.WHITE
        const val YELLOW_COLOR = Color.YELLOW
    }
}

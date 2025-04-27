package woowacourse.movie.view.selectSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatCol
import woowacourse.movie.model.ticket.seat.SeatRow
import woowacourse.movie.presenter.SelectSeatPresenter
import woowacourse.movie.view.model.SeatIndexData
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.ticket.TicketActivity

class SelectSeatActivity :
    AppCompatActivity(),
    SelectSeatView {
    private val present: SelectSeatPresenter = SelectSeatPresenter(this)
    private var dialog: AlertDialog? = null
    private val seatTableView by lazy { findViewById<TableLayout>(R.id.tl_seat) }
    private val ticketPriceTextView by lazy { findViewById<TextView>(R.id.tv_select_seat_selected_ticket_price) }
    private val confirmButtonView by lazy { findViewById<TextView>(R.id.tv_select_seat_confirm) }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            SEAT_INDEXES_DATA,
            ArrayList(
                present.selectedSeats.selectedSeats.map {
                    SeatIndexData(
                        col = it.col.value,
                        row = it.row.value,
                    )
                },
            ),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_select_seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        restoreSelectedSeat(savedInstanceState)
        present.initSelectSeatUI()
        initSeatClickListener()
        updateSubmitButton()
    }

    private fun restoreSelectedSeat(savedInstanceState: Bundle?) {
        savedInstanceState?.let { bundle ->
            val seatIndexList =
                BundleCompat.getParcelableArrayList(
                    bundle,
                    SEAT_INDEXES_DATA,
                    SeatIndexData::class.java,
                )

            seatIndexList?.forEach { seatIndexData ->
                val seat =
                    Seat(
                        row = SeatRow(seatIndexData.row),
                        col = SeatCol(seatIndexData.col),
                    )
                present.toggleSeat(seat)
            }
        }
    }

    override fun getTicketData(): TicketData =
        intent.getParcelableExtraCompat<TicketData>(EXTRA_TICKET_DATA)
            ?: throw IllegalArgumentException(ERROR_CANT_READ_TICKET_INFO)

    override fun initMovieTitleUI(ticketData: TicketData) {
        val titleView = findViewById<TextView>(R.id.tv_select_seat_movie_title)
        titleView.text = ticketData.screeningData.title
    }

    override fun initSeatClickListener() {
        for (i in 0 until seatTableView.childCount) {
            val tableRow = seatTableView.getChildAt(i) as TableRow

            for (j in 0 until tableRow.childCount) {
                val seat = tableRow.getChildAt(j) as TextView
                seat.setOnClickListener {
                    present.seatInputProcess(Seat(SeatRow(i), SeatCol(j)))
                }
            }
        }
    }

    override fun seatSelect(seat: Seat) {
        setSeatBackground(seat, R.color.selected_seat_background)
    }

    override fun seatUnSelect(seat: Seat) {
        setSeatBackground(seat, R.color.white)
    }

    override fun setTicketPrice(totalTicketPrice: TicketPrice) {
        ticketPriceTextView.text =
            getString(R.string.select_seat_total_ticket_price, totalTicketPrice.value)
    }

    private fun setSeatBackground(
        seat: Seat,
        colorRes: Int,
    ) {
        val tableRow = seatTableView.getChildAt(seat.row.value) as? TableRow
        val seatView = tableRow?.getChildAt(seat.col.value) as? TextView
        seatView?.setBackgroundResource(colorRes)
            ?: throw IllegalStateException("좌석의 좌표가 범위를 벗어났습니다")
    }

    override fun printError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToTicketUI(ticketData: TicketData) {
        startActivity(TicketActivity.newIntent(this, ticketData))
    }

    override fun updateSubmitButton() {
        if (present.isMaximumSelectedSeat()) {
            setSubmitButtonOnClickListener()
            confirmButtonView.setBackgroundResource(R.color.purple_500)
        } else {
            confirmButtonView.setOnClickListener(null)
            confirmButtonView.setBackgroundResource(R.color.disabled_btn_color)
        }
    }

    private fun setSubmitButtonOnClickListener() {
        confirmButtonView.setOnClickListener {
            dialog =
                AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.ticket_dialog_title))
                    .setMessage(getString(R.string.ticket_dialog_message))
                    .setPositiveButton(getString(R.string.ticket_dialog_positive_button)) { _, _ ->
                        present.navigateToTicketUI()
                    }.setNegativeButton(getString(R.string.ticket_dialog_nagative_button)) { dialog, _ ->
                        dialog.dismiss()
                    }.setCancelable(false)
                    .show()
        }
    }

    override fun onDestroy() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        dialog = null
        super.onDestroy()
    }

    companion object {
        private const val ERROR_CANT_READ_TICKET_INFO = "티켓 정보가 전달되지 않았습니다"

        private const val EXTRA_TICKET_DATA = "woowacourse.movie.EXTRA_TICKET_DATA"
        private const val SEAT_INDEXES_DATA = "seatIndexesData"

        fun newIntent(
            context: Context,
            ticketData: TicketData,
        ): Intent =
            Intent(context, SelectSeatActivity::class.java).apply {
                putExtra(EXTRA_TICKET_DATA, ticketData)
            }
    }
}

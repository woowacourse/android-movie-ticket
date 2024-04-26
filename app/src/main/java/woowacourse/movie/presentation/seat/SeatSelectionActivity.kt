package woowacourse.movie.presentation.seat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.SeatRepositoryImpl
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatType
import woowacourse.movie.presentation.detail.TicketDetailActivity
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.presentation.seat.SeatSelectionPresenter.Companion.KEY_NAME_SEATS
import woowacourse.movie.presentation.seat.model.MovieSeatModel
import java.io.Serializable

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var seatTable: TableLayout
    private lateinit var confirmButton: TextView
    private lateinit var movieTitleText: TextView
    private lateinit var moviePriceText: TextView
    private val presenter: SeatSelectionPresenter by lazy {
        SeatSelectionPresenter(
            view = this@SeatSelectionActivity,
            seatRepository = SeatRepositoryImpl(),
            ticketModel = getReservationTicket()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        initView()
        presenter.loadTicket()
        presenter.loadSeat()
        val saveSates =
            savedInstanceState?.getSerializable(KEY_NAME_SEATS) as? List<MovieSeatModel> ?: listOf()
        presenter.initSavedInstance(saveSates)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveInstance(outState)
    }

    private fun initView() {
        seatTable = findViewById(R.id.seat_table)
        confirmButton = findViewById(R.id.confirm_button)
        movieTitleText = findViewById(R.id.movie_title)
        moviePriceText = findViewById(R.id.movie_price)
        confirmButton.setOnClickListener {
            presenter.confirmSeatResult()
        }
    }

    private fun getReservationTicket(): TicketModel {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                MovieReservationPresenter.KEY_NAME_TICKET,
                TicketModel::class.java,
            ) ?: TicketModel.defaultTicket
        } else {
            intent.getSerializableExtra(MovieReservationPresenter.KEY_NAME_TICKET) as? TicketModel
                ?: TicketModel.defaultTicket
        }
    }

    override fun showTicket(ticketModel: TicketModel) {
        movieTitleText.text = ticketModel.title
        moviePriceText.text = ticketModel.price.toString()
    }

    override fun showSeat(seats: List<List<MovieSeat>>) {
        seatTable
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, rows ->
                rows.children.filterIsInstance<TextView>()
                    .forEachIndexed { columIndex, view ->
                        val currentSeat = seats[rowIndex][columIndex]
                        view.text = currentSeat.seatName
                        view.setTextColor(
                            when (currentSeat.seatType) {
                                SeatType.S -> getColor(R.color.seat_s_text_color)
                                SeatType.A -> getColor(R.color.seat_a_text_color)
                                SeatType.B -> getColor(R.color.seat_b_text_color)
                            }
                        )
                        view.setOnClickListener {
                            presenter.selectSeat(rowIndex, columIndex)
                        }
                    }
            }
    }

    override fun showSelectedSeat(rowIndex: Int, columnIndex: Int) {
        getTableTextView(
            rowIndex = rowIndex,
            columnIndex = columnIndex
        ).setBackgroundColor(getColor(R.color.select_seat_color))
    }

    override fun showUnSelectedSeat(rowIndex: Int, columnIndex: Int) {
        getTableTextView(
            rowIndex = rowIndex,
            columnIndex = columnIndex
        ).setBackgroundColor(getColor(R.color.white))
    }

    override fun showCurrentResultTicketPriceView(seatPrice: Int) {
        moviePriceText.text = seatPrice.toString()
    }

    override fun offConfirmAvailableView() {
        confirmButton.setBackgroundColor(getColor(R.color.seat_un_confirm_background))
    }

    override fun onConfirmAvailableView() {
        confirmButton.setBackgroundColor(getColor(R.color.seat_confirm_background))
    }

    override fun moveToTicketDetail(ticket: TicketModel) {
        val intent = Intent(this@SeatSelectionActivity, TicketDetailActivity::class.java)
        intent.putExtra(MovieReservationPresenter.KEY_NAME_TICKET, ticket as Serializable)
        this@SeatSelectionActivity.startActivity(intent)
    }

    override fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(RESERVATION_CONFIRM_TITLE)
            .setCancelable(false)
            .setMessage(RESERVATION_MESSAGE)
            .setPositiveButton(RESERVATION_OK) { _, _ ->
                presenter.ticketing()
            }
            .setNegativeButton(RESERVATION_CLOSE) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun getTableTextView(rowIndex: Int, columnIndex: Int): TextView {
        val tableRowAtIndex = seatTable.getChildAt(rowIndex) as TableRow
        return tableRowAtIndex.getChildAt(columnIndex) as TextView
    }

    companion object {
        private const val RESERVATION_CONFIRM_TITLE = "예매 확인"
        private const val RESERVATION_MESSAGE = "정말 예매하시겠습니까?"
        private const val RESERVATION_OK = "예매 완료"
        private const val RESERVATION_CLOSE = "취소"
    }
}

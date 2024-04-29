package woowacourse.movie.seats.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.list.view.MovieListActivity.Companion.EXTRA_MOVIE_ID_KEY
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_DATE_KEY
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_TIME_KEY
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.seats.presenter.SeatsPresenter
import woowacourse.movie.ticket.view.MovieTicketActivity
import java.io.Serializable

class SeatsActivity : AppCompatActivity(), SeatsContract.View {
    override val presenter: SeatsContract.Presenter = SeatsPresenter(this)
    private lateinit var seats: TableLayout
    private lateinit var title: TextView
    private lateinit var priceView: TextView
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seats)
        initView()
        initSeats()
        setOnSelectSeat()
        setOnConfirmButtonClickListener()
        processPresenterTask()
    }

    private fun processPresenterTask() {
        presenter.storeMovieId(intent.getLongExtra(EXTRA_MOVIE_ID_KEY, -1))
        presenter.storeDate(intent.getStringExtra(EXTRA_DATE_KEY) ?: "")
        presenter.storeTime(intent.getStringExtra(EXTRA_TIME_KEY) ?: "")
        presenter.setMovieTitleInfo()
        presenter.setPriceInfo()
        presenter.setSeatsTextInfo()
    }

    private fun initView() {
        title = findViewById(R.id.seats_movie_title)
        priceView = findViewById(R.id.seats_totla_price)
        confirmButton = findViewById(R.id.confirm_button)
        seats = findViewById(R.id.seats)
    }

    private fun initSeats() {
        seats.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, tableRow ->
            initRowOfSeats(tableRow, rowIndex)
        }
    }

    private fun initRowOfSeats(
        tableRow: TableRow,
        rowIndex: Int,
    ) {
        tableRow.children.filterIsInstance<TextView>().forEachIndexed { colIndex, cell ->
            presenter.createSeat(rowIndex, colIndex)
            presenter.initCell(cell)
        }
    }

    override fun initCell(
        cell: TextView,
        seat: Seat,
    ) {
        cell.text = seat.coordinate
        cell.setBackgroundColor(seat.cellBackgroundColor)
    }

    override fun setOnSelectSeat() {
        seats.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, tableRow ->
            setOnSelectRow(tableRow, rowIndex)
        }
    }

    private fun setOnSelectRow(
        tableRow: TableRow,
        rowIndex: Int,
    ) {
        tableRow.children.filterIsInstance<TextView>().forEachIndexed { colIndex, cell ->
            setOnCellClickListener(cell, rowIndex, colIndex)
        }
    }

    private fun setOnCellClickListener(
        cell: TextView,
        rowIndex: Int,
        colIndex: Int,
    ) {
        cell.setOnClickListener {
            presenter.selectSeat(rowIndex, colIndex)
            presenter.setSeatsCellsBackgroundColorInfo()
        }
    }

    override fun setSeatsText(info: Seat) {
        val row = seats.getChildAt(info.rowIndex) as TableRow
        val cell: TextView = row.getChildAt(info.colIndex) as TextView
        cell.text = info.coordinate
    }

    override fun setMovieTitle(info: String) {
        title.text = info
    }

    override fun setTotalPrice(info: Int) {
        priceView.text = TOTAL_PRICE.format(info)
    }

    private fun setOnConfirmButtonClickListener() {
        confirmButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setMessage(MESSAGE_DIALOG)
            .setNegativeButton(TEXT_CANCEL) { _, _ ->
                // nothing
            }.setPositiveButton(TEXT_CONFIRM) { _, _ ->
                presenter.startNextActivity()
            }.setCancelable(false).show()
    }

    override fun startNextActivity(
        id: Long,
        title: String,
        date: String,
        time: String,
        seats: List<Seat>,
        price: Int,
    ) {
        val intent = Intent(this, MovieTicketActivity::class.java)
        intent.putExtra(ID_KEY, id)
        intent.putExtra(TITLE_KEY, title)
        intent.putExtra(DATE_KEY, date)
        intent.putExtra(TIME_KEY, time)
        intent.putExtra(SEATS_KEY, seats as Serializable)
        intent.putExtra(PRICE_KEY, price)
        startActivity(intent)
    }

    override fun setSeatCellBackgroundColor(info: Seat) {
        val row = seats.getChildAt(info.rowIndex) as TableRow
        val cell = row.getChildAt(info.colIndex)
        cell.setBackgroundColor(info.cellBackgroundColor)
    }

    companion object {
        const val TOTAL_PRICE = "%d원"
        const val ID_KEY = "id_key"
        const val TITLE_KEY = "title_key"
        const val DATE_KEY = "date_key"
        const val TIME_KEY = "time_key"
        const val SEATS_KEY = "seats_key"
        const val PRICE_KEY = "price_key"
        private const val MESSAGE_DIALOG = "정말 예매하시겠습니까?"
        private const val TEXT_CANCEL = "취소"
        private const val TEXT_CONFIRM = "예매 완료"
    }
}

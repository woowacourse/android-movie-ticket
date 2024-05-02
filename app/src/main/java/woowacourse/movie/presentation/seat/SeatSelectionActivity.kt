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
import woowacourse.movie.presentation.model.MovieSeatModel
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.presentation.seat.SeatSelectionPresenter.Companion.KEY_NAME_SEATS
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
            pendingMovieReservationModel = getPendingMovieReservation(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        initView()
        presenter.loadData()
        val saveSates =
            savedInstanceState?.getSerializable(KEY_NAME_SEATS) as? List<MovieSeatModel> ?: listOf()
        presenter.initSavedInstanceData(saveSates)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstance(outState)
    }

    private fun initView() {
        seatTable = findViewById(R.id.seat_table)
        confirmButton = findViewById(R.id.confirm_button)
        movieTitleText = findViewById(R.id.movie_title)
        moviePriceText = findViewById(R.id.movie_price)
        confirmButton.setOnClickListener {
            presenter.confirmSeatResult()
        }
        moviePriceText.text = 0.toString()
    }

    private fun getPendingMovieReservation(): PendingMovieReservationModel {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                MovieReservationPresenter.KEY_NAME_PENDING_RESERVATION,
                PendingMovieReservationModel::class.java,
            ) ?: PendingMovieReservationModel.defaultPendingMovieReservation
        } else {
            intent.getSerializableExtra(MovieReservationPresenter.KEY_NAME_PENDING_RESERVATION) as? PendingMovieReservationModel
                ?: PendingMovieReservationModel.defaultPendingMovieReservation
        }
    }

    override fun showTicket(pendingMovieReservationModel: PendingMovieReservationModel) {
        movieTitleText.text = pendingMovieReservationModel.title
    }

    override fun showSeat(seats: List<List<MovieSeat>>) {
        seatTable
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, rows ->
                rows.children.filterIsInstance<TextView>()
                    .forEachIndexed { columIndex, view ->
                        val currentSeat = seats[rowIndex][columIndex]
                        setSeatText(view, currentSeat)
                        setSeatListener(view, rowIndex, columIndex)
                    }
            }
    }

    override fun showSelectedSeat(seat: MovieSeat) {
        getTableTextView(
            rowIndex = seat.getRowIndex(),
            columnIndex = seat.getColumnIndex(),
        ).setBackgroundColor(getColor(R.color.select_seat_color))
    }

    override fun showUnSelectedSeat(seat: MovieSeat) {
        getTableTextView(
            rowIndex = seat.getRowIndex(),
            columnIndex = seat.getColumnIndex(),
        ).setBackgroundColor(getColor(R.color.white))
    }

    override fun showCurrentResultTicketPriceView(seatPrice: Int) {
        moviePriceText.text = seatPrice.toString()
    }

    override fun offConfirmAvailable() {
        confirmButton.setBackgroundColor(getColor(R.color.seat_un_confirm_background))
    }

    override fun onConfirmAvailable() {
        confirmButton.setBackgroundColor(getColor(R.color.seat_confirm_background))
    }

    override fun moveToTicketDetail(ticket: TicketModel) {
        val intent = Intent(this@SeatSelectionActivity, TicketDetailActivity::class.java)
        intent.putExtra(SeatSelectionPresenter.KEY_NAME_TICKET, ticket as Serializable)
        this@SeatSelectionActivity.startActivity(intent)
    }

    override fun showReservationConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reservation_confirm_title))
            .setCancelable(false)
            .setMessage(getString(R.string.reservation_message))
            .setPositiveButton(getString(R.string.reservation_ok)) { _, _ ->
                presenter.ticketing()
            }
            .setNegativeButton(getString(R.string.reservation_close)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun getTableTextView(
        rowIndex: Int,
        columnIndex: Int,
    ): TextView {
        val tableRowAtIndex = seatTable.getChildAt(rowIndex) as TableRow
        return tableRowAtIndex.getChildAt(columnIndex) as TextView
    }

    private fun saveInstance(outState: Bundle) {
        val movieModels = presenter.makeSavedSeats()
        outState.putSerializable(KEY_NAME_SEATS, movieModels as Serializable)
    }

    private fun setSeatText(
        view: TextView,
        currentSeat: MovieSeat,
    ) {
        view.text = "${currentSeat.seatRow}${currentSeat.seatColumn}"
        view.setTextColor(
            when (currentSeat.seatType) {
                SeatType.S -> getColor(R.color.seat_s_text_color)
                SeatType.A -> getColor(R.color.seat_a_text_color)
                SeatType.B -> getColor(R.color.seat_b_text_color)
            },
        )
    }

    private fun setSeatListener(
        view: TextView,
        rowIndex: Int,
        columIndex: Int,
    ) {
        view.setOnClickListener {
            presenter.selectSeat(rowIndex, columIndex)
        }
    }
}

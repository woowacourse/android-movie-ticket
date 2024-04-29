package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.db.seats.SeatsDao
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seats.Grade
import woowacourse.movie.model.seats.Seat
import woowacourse.movie.model.seats.Seats
import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.presenter.reservation.SeatSelectionContract
import woowacourse.movie.presenter.reservation.SeatSelectionPresenter
import woowacourse.movie.utils.MovieUtils.bundleSerializable
import woowacourse.movie.utils.MovieUtils.convertAmountFormat
import woowacourse.movie.utils.MovieUtils.intentSerializable
import woowacourse.movie.utils.MovieUtils.makeToast
import woowacourse.movie.view.finished.ReservationFinishedActivity
import woowacourse.movie.view.home.ReservationHomeActivity
import woowacourse.movie.view.reservation.ReservationDetailActivity.Companion.TICKET

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val presenter: SeatSelectionPresenter = SeatSelectionPresenter(this, SeatsDao(), ScreeningDao())

    private val seatTableLayout: TableLayout by lazy { findViewById(R.id.table_layout_seat_selection) }
    private val title: TextView by lazy { findViewById(R.id.textview_seat_selection_title) }
    private val price: TextView by lazy { findViewById(R.id.textview_seat_selection_price) }
    private val confirmButton: Button by lazy { findViewById(R.id.button_seat_selection_confirm) }
    private lateinit var seatsTable: List<Button>
    private lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val movieId = takeMovieId()
        ticket = takeTicket()
        seatsTable = collectSeatsInTableLayout()
        with(presenter) {
            loadSeatNumber()
            loadMovie(movieId)
        }
        initializeConfirmButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putSerializable(TICKET, ticket)
            putSerializable(SEATS, presenter.seats)
            putIntegerArrayList(SEATS_INDEX, ArrayList(presenter.seats.seatsIndex))
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let { bundle ->
            runCatching {
                restoreSeatsData(bundle)
                restoreReservationData(bundle)
            }.onFailure {
                showErrorToast()
                finish()
            }
        }
    }

    override fun restoreSelectedSeats(selectedSeats: List<Int>) {
        seatsTable.forEachIndexed { index, button ->
            button.isSelected = index in selectedSeats
        }
    }

    override fun initializeSeatsTable(
        index: Int,
        seat: Seat,
    ) {
        seatsTable[index].apply {
            showSeatNumber(seat)
            updateReservationInformation(index, seat)
        }
    }

    override fun Button.showSeatNumber(seat: Seat) {
        text = getString(R.string.select_seat_number, seat.row, seat.column)
        setTextColor(setUpSeatColorByGrade(seat.grade))
    }

    override fun Button.updateReservationInformation(
        index: Int,
        seat: Seat,
    ) {
        setOnClickListener {
            if (getSeatsCount() < ticket.count || isSelected) {
                updateSeatSelectedState(index, isSelected)
                presenter.manageSelectedSeats(isSelected, index, seat)
                presenter.updateTotalPrice(isSelected, seat)
                setConfirmButtonEnabled(getSeatsCount())
            }
        }
    }

    override fun setUpSeatColorByGrade(grade: Grade): Int {
        return when (grade) {
            Grade.B -> getColor(R.color.purple_500)
            Grade.S -> getColor(R.color.teal_700)
            Grade.A -> getColor(R.color.blue)
        }
    }

    override fun updateSeatSelectedState(
        index: Int,
        isSelected: Boolean,
    ) {
        seatsTable[index].isSelected = !isSelected
    }

    override fun showMovieTitle(movie: Movie) {
        title.text = movie.title
    }

    override fun showAmount(amount: Int) {
        price.text = convertAmountFormat(this, amount)
    }

    override fun navigateToFinished(seats: Seats) {
        val intent = Intent(this, ReservationFinishedActivity::class.java)
        intent.putExtra(TICKET, ticket)
        intent.putExtra(SEATS, seats)
        startActivity(intent)
    }

    override fun setConfirmButtonEnabled(count: Int) {
        confirmButton.isEnabled = count >= ticket.count
    }

    override fun launchReservationConfirmDialog(seats: Seats) {
        if (confirmButton.isEnabled) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.seat_selection_reservation_confirm))
                .setMessage(getString(R.string.seat_selection_reservation_ask_purchase_ticket))
                .setPositiveButton(getString(R.string.seat_selection_reservation_finish)) { _, _ ->
                    navigateToFinished(seats)
                }
                .setNegativeButton(getString(R.string.seat_selection_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }

    override fun showErrorToast() = makeToast(this, getString(R.string.all_error))

    private fun takeMovieId() =
        intent.getIntExtra(
            ReservationHomeActivity.MOVIE_ID,
            ReservationDetailActivity.DEFAULT_MOVIE_ID,
        )

    private fun takeTicket(): Ticket = intent.intentSerializable(TICKET, Ticket::class.java) ?: Ticket()

    private fun collectSeatsInTableLayout(): List<Button> =
        seatTableLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<Button>().toList()

    private fun getSeatsCount(): Int = seatsTable.count { seat -> seat.isSelected }

    private fun initializeConfirmButton() {
        confirmButton.setOnClickListener {
            presenter.initializeConfirmButton()
        }
    }

    private fun restoreReservationData(bundle: Bundle) {
        val ticket = bundle.bundleSerializable(TICKET, Ticket::class.java) ?: throw NoSuchElementException()
        presenter.restoreReservation(ticket.count)
    }

    private fun restoreSeatsData(bundle: Bundle) {
        val seats = bundle.bundleSerializable(SEATS, Seats::class.java) ?: throw NoSuchElementException()
        val index = bundle.getIntegerArrayList(SEATS_INDEX) ?: throw NoSuchElementException()
        presenter.restoreSeats(seats, index.toList())
    }

    companion object {
        const val SEATS = "seats"
        const val SEATS_INDEX = "seatsIndex"
    }
}

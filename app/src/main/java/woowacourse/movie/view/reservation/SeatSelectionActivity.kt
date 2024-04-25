package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.db.SeatsDao
import woowacourse.movie.model.Grade
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.reservation.SeatSelectionContract
import woowacourse.movie.presenter.reservation.SeatSelectionPresenter
import woowacourse.movie.view.finished.ReservationFinishedActivity
import woowacourse.movie.view.home.ReservationHomeActivity
import woowacourse.movie.view.reservation.ReservationDetailActivity.Companion.TICKET
import java.io.Serializable
import java.text.DecimalFormat

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

        val movieId =
            intent.getIntExtra(
                ReservationHomeActivity.MOVIE_ID,
                ReservationDetailActivity.DEFAULT_MOVIE_ID,
            )
        ticket = intent.intentSerializable(TICKET, Ticket::class.java) ?: Ticket()
        seatsTable =
            seatTableLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<Button>().toList()

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
        savedInstanceState.let {
            val ticket = it.bundleSerializable(TICKET, Ticket::class.java) ?: Ticket()
            val seats = it.bundleSerializable(SEATS, Seats::class.java) ?: Seats()
            val index = it.getIntegerArrayList(SEATS_INDEX) ?: emptyList<Int>()
            with(presenter) {
                restoreSeats(seats, index.toList())
                restoreReservation(ticket.count)
            }
        }
    }

    override fun restoreSelectedSeats(selectedSeats: List<Int>) {
        seatsTable.forEachIndexed { index, button ->
            button.isSelected = index in selectedSeats
        }
    }

    override fun showSeatNumber(
        index: Int,
        seat: Seat,
    ) {
        seatsTable[index].apply {
            text = getString(R.string.select_seat_number).format(seat.row, seat.column)
            setTextColor(setUpSeatColorByGrade(seat.grade))
            setOnClickListener {
                val seatsCount = seatsTable.count { seat -> seat.isSelected }
                if (seatsCount < ticket.count || it.isSelected) {
                    updateSeatSelectedState(index, isSelected)
                    val updatedSeatsCount = seatsTable.count { seat -> seat.isSelected }
                    presenter.manageSelectedSeats(it.isSelected, index, seat)
                    presenter.updateTotalPrice(it.isSelected, seat)
                    setConfirmButtonEnabled(updatedSeatsCount)
                }
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

    override fun showTotalPrice(amount: Int) {
        price.text = DecimalFormat(getString(R.string.all_price)).format(amount)
    }

    override fun navigateToFinished(seats: Seats) {
        val intent = Intent(this, ReservationFinishedActivity::class.java)
        intent.putExtra(TICKET, ticket)
        intent.putExtra("seats", seats)
        startActivity(intent)
    }

    override fun setConfirmButtonEnabled(count: Int) {
        confirmButton.isEnabled = count >= ticket.count
    }

    private fun initializeConfirmButton() {
        confirmButton.setOnClickListener {
            presenter.initializeConfirmButton()
        }
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

    private fun <T : Serializable> Bundle.bundleSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializable(key, clazz)
        } else {
            this.getSerializable(key) as T?
        }
    }

    private fun <T : Serializable> Intent.intentSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }

    companion object {
        const val SEATS = "seats"
        const val SEATS_INDEX = "seatsIndex"
    }
}

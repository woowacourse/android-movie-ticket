package woowacourse.movie.presentation.choiceSeat

import android.content.Context
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
import woowacourse.movie.data.MovieData
import woowacourse.movie.domain.model.rules.SeatsPayment
import woowacourse.movie.domain.model.tools.Money
import woowacourse.movie.domain.model.tools.seat.Location
import woowacourse.movie.domain.model.tools.seat.Seat
import woowacourse.movie.domain.model.tools.seat.SeatGrade
import woowacourse.movie.domain.model.tools.seat.SeatRow
import woowacourse.movie.domain.model.tools.seat.Seats
import woowacourse.movie.domain.model.tools.seat.Theater
import woowacourse.movie.presentation.complete.CompleteActivity
import woowacourse.movie.presentation.mappers.toPresentation
import woowacourse.movie.presentation.model.ReservationModel

class ChoiceSeatActivity : AppCompatActivity() {

    private val seats: Seats = Seats()
    private var paymentAmount: Money = Money(INITIAL_PAYMENT_AMOUNT)
    private val reservation by lazy {
        initReservation()
    }
    private val confirmButton by lazy {
        findViewById<Button>(R.id.buttonChoiceConfirm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_seat)
        setTheaterSeat()
        initView()
    }

    private fun initReservation() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(RESERVATION, ReservationModel::class.java)
            ?: throw IllegalArgumentException()
    } else {
        intent.getParcelableExtra(RESERVATION) ?: throw IllegalArgumentException()
    }

    private fun initView() {
        val movie = MovieData.findMovieById(reservation.movieId)
        setTitle(movie.title)
        setPaymentAmount(Money(INITIAL_PAYMENT_AMOUNT))
        setConfirmButton()
    }

    private fun setConfirmButton() {
        confirmButton.setOnClickListener {
            showConfirmCheckDialog()
        }
        confirmButton.isEnabled = false
    }

    private fun showConfirmCheckDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_choice_confirm_title))
            .setMessage(getString(R.string.dialog_choice_confirm_message))
            .setPositiveButton(getString(R.string.dialog_choice_positive_button)) { _, _ ->
                confirmBookMovie()
            }
            .setNegativeButton(getString(R.string.dialog_choice_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun confirmBookMovie() {
        val movie = MovieData.findMovieById(reservation.movieId).toPresentation()
        val ticketModel = movie.reserve(reservation, seats)
        startActivity(CompleteActivity.getIntent(this, ticketModel))
    }

    private fun setTitle(title: String) {
        findViewById<TextView>(R.id.textChoiceTitle).text = title
    }

    private fun setPaymentAmount(amount: Money) {
        paymentAmount = amount
        findViewById<TextView>(R.id.textChoicePaymentAmount).text =
            getString(R.string.payment_amount, amount.value)
    }

    private fun setTheaterSeat() {
        val theater = Theater.of(rows, columns)
        val seatsTable = findViewById<TableLayout>(R.id.tableSeats)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEachIndexed { index, view ->
                setSeat(index, view, theater)
            }
    }

    private fun setSeat(index: Int, view: TextView, theater: Theater) {
        val column = index % THEATER_COLUMN
        val location = Location(indexToRow(index), column)
        val seat = requireNotNull(theater.findSeat(location))

        setSeatText(view, location)
        setSeatTextColor(view, seat.grade)
        view.setOnClickListener {
            clickSeat(seat, view)
        }
    }

    private fun clickSeat(seat: Seat, view: TextView) {
        when {
            view.isSelected -> unSelectSeat(seat, view)
            seats.size >= reservation.count -> return
            else -> selectSeat(seat, view)
        }
    }

    private fun unSelectSeat(seat: Seat, view: TextView) {
        removeSeat(seat)
        view.isSelected = false
    }

    private fun selectSeat(seat: Seat, view: TextView) {
        addSeat(seat)
        view.isSelected = true
    }

    private fun addSeat(seat: Seat) {
        seats.addSeat(seat)
        val paymentMoney =
            SeatsPayment(seats).getDiscountedMoneyByDateTime(reservation.bookedDateTime)
        setPaymentAmount(paymentMoney)
        checkCountEqual()
    }

    private fun removeSeat(seat: Seat) {
        seats.removeSeat(seat)
        val paymentMoney =
            SeatsPayment(seats).getDiscountedMoneyByDateTime(reservation.bookedDateTime)
        setPaymentAmount(paymentMoney)
        checkCountEqual()
    }

    private fun checkCountEqual() {
        if (seats.size == reservation.count) {
            confirmButton.isEnabled = true
            return
        }
        confirmButton.isEnabled = false
    }

    private fun setSeatText(view: TextView, location: Location) {
        view.text =
            getString(
                R.string.seat_format,
                location.row,
                (location.number + COLUMN_ADDITION).toString(),
            )
    }

    private fun setSeatTextColor(view: TextView, seatGrade: SeatGrade) {
        when (seatGrade) {
            SeatGrade.GRADE_B -> view.setTextColor(getColor(R.color.purple_400))
            SeatGrade.GRADE_S -> view.setTextColor(getColor(R.color.green_300))
            SeatGrade.GRADE_A -> view.setTextColor(getColor(R.color.blue_700))
        }
    }

    private fun indexToRow(index: Int): SeatRow {
        val rows = SeatRow.values().toList()
        return rows[index / THEATER_COLUMN]
    }

    companion object {
        private const val THEATER_COLUMN = 4
        private const val COLUMN_ADDITION = 1
        private const val INITIAL_PAYMENT_AMOUNT = 0
        private val rows = SeatRow.values().toList()
        private val columns = List(THEATER_COLUMN) { it }
        private const val RESERVATION = "RESERVATION"

        fun getIntent(context: Context, reservation: ReservationModel): Intent {
            return Intent(context, ChoiceSeatActivity::class.java).apply {
                putExtra(RESERVATION, reservation)
            }
        }
    }
}

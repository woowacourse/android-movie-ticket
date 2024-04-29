package woowacourse.movie.ui.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TimeReservation
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.domain.repository.DummySeats
import woowacourse.movie.ui.Currency
import woowacourse.movie.ui.reservation.ReservationCompleteActivity
import java.util.Locale

class SeatReservationActivity : AppCompatActivity(), SeatReservationContract.View {
    private val presenter = SeatReservationPresenter(this, DummyScreens(DummySeats()), DummyReservation)
    private val seatsGridLayout: GridLayout by lazy { findViewById(R.id.gl_seat_reservation_seats) }

    private val movieTitle: TextView by lazy { findViewById(R.id.tv_seat_reservation_movie_title) }
    private val totalPrice: TextView by lazy { findViewById(R.id.tv_seat_reservation_total_price) }
    private val reserveCompleteBtn: TextView by lazy { findViewById(R.id.btn_seat_reservation_complete) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        val id = intent.getIntExtra(TIME_RESERVATION_ID, DEFAULT_ID)
        presenter.loadSeats(id)
        presenter.loadTimeReservations(id)
        totalPrice.text = "0원"

        reserveCompleteBtn.setOnClickListener {
            presenter.reserve(id)
        }
    }

    override fun showSeats(seats: Seats) {
        val maxRow = seats.maxRow()
        val maxColumn = seats.maxColumn()
        seatsGridLayout.columnCount = maxColumn
        seatsGridLayout.rowCount = maxRow

        for (row in 0 until maxRow) {
            for (column in 0 until maxColumn) {
                val textView =
                    TextView(this).apply {
                        layoutParams =
                            GridLayout.LayoutParams().apply {
                                width = 0
                                height = 0
                                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                            }
                        setBackgroundResource(R.drawable.holder_seat_selector)
                        setOnClickListener {
                            presenter.selectSeat(position = Position(row, column), this)
                        }
                        gravity = Gravity.CENTER
                        text = "${'A' + row} ${column + 1}"
                    }
                seatsGridLayout.addView(textView)
            }
        }
    }

    override fun showTimeReservations(timeReservation: TimeReservation) {
        val screen = timeReservation.screen
        movieTitle.text = screen.movie.title
    }

    override fun showTotalPrice(seats: Seats) {
        totalPrice.text = Currency.of(Locale.getDefault().country).format(seats.totalPrice())
    }

    override fun activateReservation(boolean: Boolean) {
        if (boolean) {
            reserveCompleteBtn.isEnabled = true
            reserveCompleteBtn.setBackgroundColor(getColor(R.color.complete_activated))
        } else {
            reserveCompleteBtn.isEnabled = false
            reserveCompleteBtn.setBackgroundColor(getColor(R.color.complete_deactivated))
        }
    }

    override fun showToast(e: Throwable) {
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToCompleteReservation(reservationId: Int) {
        ReservationCompleteActivity.startActivity(this, reservationId)
    }

    override fun showSeatReservationFail(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TIME_RESERVATION_ID = "timeReservationId"
        private const val DEFAULT_ID = -1

        fun startActivity(
            context: Context,
            timeReservationId: Int,
        ) {
            val intent =
                Intent(context, SeatReservationActivity::class.java).apply {
                    putExtra(TIME_RESERVATION_ID, timeReservationId)
                }
            context.startActivity(intent)
        }
    }
}

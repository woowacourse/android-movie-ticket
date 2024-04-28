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
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.domain.repository.DummySeats

class SeatReservationActivity : AppCompatActivity(), SeatReservationContract.View {
    private val presenter = SeatReservationPresenter(this, DummyScreens(DummySeats()), DummyReservation)
    private val seatsGridLayout: GridLayout by lazy { findViewById(R.id.gl_seat_reservation_seats) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        presenter.loadSeats(1)
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
                            Toast.makeText(context, "Clicked row: $row , col: $column", Toast.LENGTH_SHORT).show()
                            isSelected = !isSelected // 선택 상태 토글
                        }
                        gravity = Gravity.CENTER
                        text = "${'A' + row} ${column + 1}"
                    }
                seatsGridLayout.addView(textView)
            }
        }
    }

    override fun navigateToCompleteReservation(reservationId: Int) {
        TODO("Not yet implemented")
    }

    override fun showSeatReservationFail(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PUT_EXTRA_KEY_RESERVATION_ID = "reservationId"
        private const val DEFAULT_RESERVATION_ID = -1

        fun startActivity(
            context: Context,
            reservationId: Int,
        ) {
            val intent = Intent(context, SeatReservationActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}

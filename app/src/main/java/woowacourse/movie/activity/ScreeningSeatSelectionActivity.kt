package woowacourse.movie.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import domain.movie.MovieName
import domain.reservation.Reservation
import domain.reservation.SeatReservation
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import domain.seat.SeatState
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity.Companion.SEAT_RESERVATION_KEY
import woowacourse.movie.model.SeatReservationInfo
import woowacourse.movie.model.toDomainModel
import woowacourse.movie.model.toUIModel

class ScreeningSeatSelectionActivity : AppCompatActivity() {

    private val seatReservationInfo: SeatReservationInfo by lazy {
        intent.customGetSerializable(SEAT_RESERVATION_KEY) as? SeatReservationInfo
            ?: run {
                Toast.makeText(
                    this,
                    getString(R.string.movie_data_error_message),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
                SeatReservationInfo.ofError()
            }
    }
    private val seatReservation: SeatReservation by lazy {
        seatReservationInfo.toDomainModel()
    }

    private val paymentAmountText: TextView by lazy {
        findViewById(R.id.seat_selection_payment_amount_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_seat_selection)
        initView()
        initSeatClickListener()
        initSelectionCompleteButton()
    }

    private fun initView() {
        val movieNamTextView = findViewById<TextView>(R.id.seat_selection_movie_name_text)

        movieNamTextView.text = seatReservationInfo.movieName
    }

    private fun initSeatClickListener() {
        val seatButtons = findViewById<TableLayout>(R.id.seat_button)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        seatButtons.forEachIndexed { index, seatSelectionButton ->
            seatSelectionButton.setOnClickListener {
                // TODO: PAYMENT가져오는 방법, 네이밍
                val row = SeatRow.values().find { index / 4 == it.ordinal }
                    ?: throw IllegalArgumentException("없는행입니다.")
                val col = SeatColumn.values().find { index % 4 == it.ordinal }
                    ?: throw IllegalArgumentException("없는열입니다.")
                val seat = ScreeningSeat(row, col)

                val clickedSeatState = seatReservation.screeningSeats.values[seat]

                clickedSeatState?.let {
                    runCatching {
                        if (it == SeatState.RESERVED) {
                            seatSelectionButton.setBackgroundColor(Color.WHITE)
                            seatReservation.cancelSeat(seat)
                        } else {
                            seatSelectionButton.setBackgroundColor(Color.YELLOW)
                            seatReservation.selectSeat(seat)
                        }
                    }.onFailure {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                paymentAmountText.text = seatReservation.getTotalPaymentAmount().value.toString()
            }
        }
    }

    private fun initSelectionCompleteButton() {
        val seatSelectionCompleteButton = findViewById<Button>(R.id.seat_selection_complete_button)

        seatSelectionCompleteButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예") { _, _ -> onCompleteSeatSelection() }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // todo key
    private fun onCompleteSeatSelection() {
        kotlin.runCatching {
            val intent = Intent(this, ReservationResultActivity::class.java)
            val reservation = Reservation.of(
                MovieName(seatReservationInfo.movieName),
                seatReservation.selectingCount,
                // todo 변수명 screeningDataTime
                seatReservation.screeningTime,
                seatReservation.getTotalPaymentAmount(),
                seatReservation.selectingComplete()
            ).toUIModel()

            intent.putExtra(RESERVATION_KEY, reservation)
            startActivity(intent)
        }.onFailure {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val RESERVATION_KEY = "reservation_key"
    }
}

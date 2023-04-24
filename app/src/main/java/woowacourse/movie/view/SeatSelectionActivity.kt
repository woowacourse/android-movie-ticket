package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import woowacourse.movie.R
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.service.MovieService
import woowacourse.movie.view.ReservationResultActivity.Companion.RESERVATION_ID
import kotlin.properties.Delegates

class SeatSelectionActivity : AppCompatActivity() {

    private val reservationInfo: ReservationInfo by lazy { initReservationInfoFromIntent() }
    private var selectedSeats: Set<Pair<Int, Int>> by Delegates.observable(setOf()) { _, _, new ->
        val reservationFee = MovieService.calculateReservationFee(
            reservationInfo.movieId,
            reservationInfo.screeningDateTime,
            new
        )
        val reservationFeeView = findViewById<TextView>(R.id.reservation_fee)
        reservationFeeView.text =
            getString(R.string.fee_format).format(DECIMAL_FORMAT.format(reservationFee))
        reservationButton.isEnabled = new.isNotEmpty()
        if (reservationButton.isEnabled) reservationButton.setBackgroundColor(getColor(R.color.enabled_button_color))
        else reservationButton.setBackgroundColor(getColor(R.color.disabled_button_color))
    }
    private val reservationButton: Button by lazy {
        findViewById(R.id.reservation_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        initViewData()
        reservationButton.setOnClickListener {
            val reservationResultId = MovieService.reserve(
                reservationInfo.movieId,
                reservationInfo.screeningDateTime,
                selectedSeats
            )
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra(RESERVATION_ID, reservationResultId)
            startActivity(intent)
        }
    }

    private fun initReservationInfoFromIntent(): ReservationInfo {
        val reservationInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(RESERVATION_INFO, ReservationInfo::class.java)
        } else {
            intent.getSerializableExtra(RESERVATION_INFO) as ReservationInfo?
        }
        requireNotNull(reservationInfo) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        return reservationInfo
    }

    private fun initViewData() {
        val movieTitleView = findViewById<TextView>(R.id.movie_title)
        movieTitleView.text = MovieService.findMovieById(reservationInfo.movieId).title
        val reservationFee = findViewById<TextView>(R.id.reservation_fee)
        reservationFee.text = getString(R.string.fee_format).format(0)
        initSeatButtons()
    }

    private fun initSeatButtons() {
        val seatTable = findViewById<TableLayout>(R.id.seat_table)
        val movie = MovieService.findMovieById(reservationInfo.movieId)
        val screening =
            movie.screenings.find { it.screeningDateTime == reservationInfo.screeningDateTime }
                ?: throw IllegalArgumentException("상영 정보가 없다면 어딘가에서 로직이 잘못되었습니다.")
        val seats = screening.seats.toSet()
        val maxRow = seats.maxOf { it.row }
        val maxColumn = seats.maxOf { it.column }
        for (row in 1..maxRow) {
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, 1f)
            }
            for (column in 1..maxColumn) {
                tableRow.addView(createSeat(this, row, column, seats))
            }
            seatTable.addView(tableRow)
        }
    }

    private fun createSeat(
        context: Context,
        row: Int,
        column: Int,
        seats: Set<SeatDto>
    ): AppCompatButton =
        AppCompatButton(context).apply {
            val seat =
                seats.find { it.row == row && it.column == column } ?: SeatDto(0, 0, "None")
            text = seat.getName()
            setTextColor(getColor(seat.getTextColor()))
            setOnClickListener { onSeatClick(this) }
            setBackgroundColor(getColor(R.color.unselected_seat_color))
            layoutParams = TableRow.LayoutParams(0, Toolbar.LayoutParams.MATCH_PARENT, 1f)
        }

    private fun SeatDto.getName(): String =
        if (row == 0 && column == 0) getString(R.string.not_exist_seat_point)
        else ('A' + (row - 1)).toString() + column

    private fun SeatDto.getTextColor(): Int =
        when (this.seatClass) {
            "S" -> R.color.s_class_color
            "A" -> R.color.a_class_color
            "B" -> R.color.b_class_color
            else -> R.color.black
        }

    private fun onSeatClick(seat: Button) {
        if (seat.isSelected) {
            deselectSeat(seat)
            return
        }
        selectSeat(seat)
    }

    private fun deselectSeat(seat: Button) {
        selectedSeats = selectedSeats - seat.text.toPoint()
        seat.setBackgroundColor(getColor(R.color.unselected_seat_color))
        seat.isSelected = false
    }

    private fun selectSeat(seat: Button) {
        selectedSeats = selectedSeats + seat.text.toPoint()
        seat.setBackgroundColor(getColor(R.color.selected_seat_color))
        seat.isSelected = true
    }

    private fun CharSequence.toPoint(): Pair<Int, Int> {
        return this[0] - 'A' + 1 to this[1].toString().toInt()
    }

    companion object {
        const val RESERVATION_INFO = "RESERVATION_INFO"
    }
}

package woowacourse.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import androidx.core.view.children
import com.example.domain.ReservationAgency
import com.example.domain.Seat
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.util.getParcelableCompat
import java.text.DecimalFormat

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeatSelectionBinding
    private val reservationOptions by lazy {
        intent.getParcelableCompat<ReservationOptions>(ReservationActivity.RESERVATION_OPTIONS)
    }
    private lateinit var reservationAgency: ReservationAgency
    private var selectedSeatCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSeatButtons()
        initReserveLayout()
        initReservationAgency()
    }

    private fun initSeatButtons() {
        val seats = binding.seatTablelayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>()
            .toList()
        val seatNames = resources.getStringArray(R.array.seats)
        seats.forEachIndexed { index, seat ->
            seat.text = seatNames[index]
            seat.setOnClickListener {
                onSeatClick(it as Button)
            }
        }
    }

    private fun onSeatClick(seat: Button) {
        if (seat.isSelected) {
            deselectSeat(seat)
            return
        }
        selectSeat(seat)
    }

    private fun deselectSeat(seat: Button) {
        selectedSeatCount--
        seat.isSelected = false
        binding.confirmReservationButton.isEnabled = false
        binding.reservationFeeTextview.text = getString(R.string.reservation_fee_format).format(
            DECIMAL_FORMAT.format(0)
        )
    }

    private fun selectSeat(seat: Button) {
        reservationOptions?.let {
            if (selectedSeatCount < it.peopleCount) {
                seat.isSelected = true
                selectedSeatCount++
                if (selectedSeatCount == it.peopleCount) {
                    onSelectionComplete()
                    return
                }
            }
        }
    }

    private fun onSelectionComplete() {
        val seats = binding.seatTablelayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>()
            .toList()

        val selectedSeats = findSelectedSeats(seats)
        if (reservationAgency.canReserve(selectedSeats)) {
            val reservationFee = reservationAgency.calculateReservationFee(selectedSeats)
            setReservationFee(reservationFee.amount)
        }
        binding.confirmReservationButton.isEnabled = true
    }

    private fun findSelectedSeats(seats: List<Button>): List<Seat> {
        val selectedSeats = mutableListOf<Seat>()
        seats.forEachIndexed { index, button ->
            if (!button.isSelected) return@forEachIndexed
            selectedSeats.add(
                Seat(
                    index % Seat.MAX_COLUMN + 1,
                    index / Seat.MAX_COLUMN + 1
                )
            )
        }
        return selectedSeats
    }

    private fun setReservationFee(fee: Int) {
        binding.reservationFeeTextview.text = getString(R.string.reservation_fee_format).format(
            DECIMAL_FORMAT.format(fee)
        )
    }

    private fun initReserveLayout() {
        binding.apply {
            movieTitleTextview.text = reservationOptions?.title
            reservationFeeTextview.text = getString(R.string.reservation_fee_format).format(
                DECIMAL_FORMAT.format(0)
            )
            confirmReservationButton.isEnabled = false
        }
    }

    private fun initReservationAgency() {
        val movie =
            intent.getParcelableCompat<MovieUiModel>(ReservationActivity.MOVIE)?.toDomainModel()

        if (movie != null && reservationOptions != null) {
            reservationAgency = ReservationAgency(
                movie,
                reservationOptions!!.peopleCount,
                reservationOptions!!.screeningDateTime
            )
        }
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}

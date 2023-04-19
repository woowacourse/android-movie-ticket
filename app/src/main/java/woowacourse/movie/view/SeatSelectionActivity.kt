package woowacourse.movie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.domain.ReservationAgency
import com.example.domain.Seat
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.util.getParcelableCompat
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.ReservationOptions
import woowacourse.movie.view.model.toDomainModel
import woowacourse.movie.view.model.toUiModel
import java.text.DecimalFormat

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeatSelectionBinding
    private val reservationOptions by lazy {
        intent.getParcelableCompat<ReservationOptions>(ReservationActivity.RESERVATION_OPTIONS)
    }
    private lateinit var reservationAgency: ReservationAgency
    private var selectedSeatCount = 0
    private var selectedSeats: List<Seat> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSeatButtons()
        initReserveLayout()
        initReservationAgency()
        initConfirmReservationButton()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

        selectedSeats = findSelectedSeats(seats)
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

    private fun initConfirmReservationButton() {
        binding.confirmReservationButton.setOnClickListener {
            val alertDialog: AlertDialog = AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.reservation_dialog_title))
                setMessage(getString(R.string.reservation_dialog_message))
                setPositiveButton(getString(R.string.confirm_reservation)) { _, _ ->
                    reserveSeats()
                }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                setCancelable(false)
            }.create()
            alertDialog.show()
        }
    }

    private fun reserveSeats() {
        val reservation = reservationAgency.reserve(selectedSeats)
        val intent = Intent(this, ReservationCompletedActivity::class.java)
        intent.putExtra(RESERVATION, reservation?.toUiModel())
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val RESERVATION = "RESERVATION"
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}

package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toolbar.LayoutParams
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.domain.ReservationAgency
import woowacourse.movie.domain.Seat
import woowacourse.movie.util.getParcelableCompat
import woowacourse.movie.view.mapper.toDomainModel
import woowacourse.movie.view.mapper.toUiModel
import woowacourse.movie.view.model.MovieListModel.MovieUiModel
import woowacourse.movie.view.model.ReservationOptions
import woowacourse.movie.view.model.SeatUiModel
import java.text.DecimalFormat

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeatSelectionBinding
    private val reservationOptions by lazy {
        intent.getParcelableCompat<ReservationOptions>(RESERVATION_OPTIONS)
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
        for (row in Seat.MIN_ROW..Seat.MAX_ROW) {
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, 1f)
            }
            for (col in Seat.MIN_COLUMN..Seat.MAX_COLUMN) {
                val seat = Seat(col, row)
                tableRow.addView(createSeat(this, seat.toUiModel()))
            }
            binding.seatTablelayout.addView(tableRow)
        }
    }

    private fun createSeat(context: Context, seatUi: SeatUiModel): AppCompatButton =
        AppCompatButton(context).apply {
            text = seatUi.name
            setTextColor(getColor(seatUi.color))
            setOnClickListener { onSeatClick(this) }
            background =
                AppCompatResources.getDrawable(this@SeatSelectionActivity, R.drawable.selector_seat)
            layoutParams = TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
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
            intent.getParcelableCompat<MovieUiModel>(MOVIE)?.toDomainModel()

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
        reservation?.let {
            startActivity(ReservationCompletedActivity.newIntent(this, reservation.toUiModel()))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RESERVATION_OPTIONS = "RESERVATION_OPTIONS"
        private const val MOVIE = "MOVIE"
        private val DECIMAL_FORMAT = DecimalFormat("#,###")

        fun newIntent(
            context: Context,
            reservationOptions: ReservationOptions,
            movie: MovieUiModel
        ): Intent {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(RESERVATION_OPTIONS, reservationOptions)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }
}

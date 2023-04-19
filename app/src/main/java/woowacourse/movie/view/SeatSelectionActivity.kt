package woowacourse.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.util.getParcelableCompat
import java.text.DecimalFormat

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeatSelectionBinding
    private val reservationOptions by lazy {
        intent.getParcelableCompat<ReservationOptions>(ReservationActivity.RESERVATION_OPTIONS)
    }
    private var reservationFee = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSeatButtons()
        initReserveLayout()
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
                it.isSelected = !it.isSelected
            }
        }
    }

    private fun initReserveLayout() {
        binding.apply {
            movieTitleTextview.text = reservationOptions?.title
            reservationFeeTextview.text = getString(R.string.reservation_fee_format).format(
                DECIMAL_FORMAT.format(reservationFee)
            )
            confirmReservationButton.isEnabled = false
        }
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}

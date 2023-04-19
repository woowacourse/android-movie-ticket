package woowacourse.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationCompletedBinding
import woowacourse.movie.util.getParcelableCompat
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservation =
            intent.getParcelableCompat<ReservationUiModel>(SeatSelectionActivity.RESERVATION)
        reservation?.let { initViewData(it) }
    }

    private fun initViewData(reservation: ReservationUiModel) {
        binding.apply {
            movieTitle.text = reservation.title
            movieScreeningDate.text = reservation.screeningDateTime.format(DATE_TIME_FORMATTER)
            peopleCount.text = getString(R.string.reservation_people_count_format)
                .format(
                    getString(R.string.general_person),
                    reservation.peopleCount,
                    reservation.seats.joinToString()
                )
            totalPrice.text =
                getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservation.finalReservationFee))
        }
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}

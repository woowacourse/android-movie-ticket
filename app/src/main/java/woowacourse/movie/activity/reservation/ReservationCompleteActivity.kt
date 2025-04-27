package woowacourse.movie.activity.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationCompleteBinding
import woowacourse.movie.dto.ReservationSeatDto
import woowacourse.movie.global.getObjectFromIntent
import woowacourse.movie.global.toFormattedString

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityReservationCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking_success)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val reservationDto = intent.getObjectFromIntent<ReservationSeatDto>(BOOKING_STATUS_KEY)

        binding.bookedMovieTitleText.text = reservationDto.reservationDto.movie.title
        binding.bookedMovieRunningDayText.text = reservationDto.reservationDto.bookedTime.toFormattedString()
        binding.memberCountText.text =
            getString(
                R.string.member_count,
                reservationDto.reservationDto.memberCount,
            )
        binding.bookedMovieTicketPriceText.text =
            getString(
                R.string.total_price,
                reservationDto.totalPrice,
                reservationDto.seats.joinToString(", ") { it.location },
            )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val BOOKING_STATUS_KEY = "bookingStatus"

        fun newIntent(
            from: Context,
            dto: ReservationSeatDto,
        ): Intent {
            return Intent(from, ReservationCompleteActivity::class.java).apply {
                putExtra(BOOKING_STATUS_KEY, dto)
            }
        }
    }
}

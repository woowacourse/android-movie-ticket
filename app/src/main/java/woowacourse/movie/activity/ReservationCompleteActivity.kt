package woowacourse.movie.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationCompleteBinding
import woowacourse.movie.domain.BookingStatus
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
        val bookingStatus = intent.getObjectFromIntent<BookingStatus>(BOOKING_STATUS_KEY)

        binding.bookedMovieTitleText.text = bookingStatus.movie.title
        binding.bookedMovieRunningDayText.text = bookingStatus.bookedTime.toFormattedString()
        binding.memberCountText.text =
            binding.memberCountText.context.getString(
                R.string.member_count,
                bookingStatus.memberCount.value,
            )
        binding.bookedMovieTicketPriceText.text =
            binding.bookedMovieTicketPriceText.context.getString(
                R.string.total_price,
                bookingStatus.calculateTicketPrices(),
            )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val BOOKING_STATUS_KEY = "bookingStatus"

        fun newIntent(
            from: Activity,
            dto: BookingStatus,
        ): Intent {
            return Intent(from, ReservationCompleteActivity::class.java).apply {
                putExtra(BOOKING_STATUS_KEY, dto)
            }
        }
    }
}

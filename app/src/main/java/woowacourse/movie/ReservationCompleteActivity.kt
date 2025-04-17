package woowacourse.movie

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.databinding.BookingSuccessBinding
import woowacourse.movie.domain.BookingStatus

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = BookingSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking_success)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bookingStatus = bookingStatus()

        binding.bookedMovieTitleText.text = bookingStatus.movie.title
        binding.bookedMovieRunningDayText.text =
            binding.bookedMovieRunningDayText.context.getString(
                R.string.movie_running_dateTime,
                bookingStatus.bookedTime
            )
        binding.memberCountText.text = binding.memberCountText.context.getString(
            R.string.member_count,
            bookingStatus.memberCount.value
        )
        binding.bookedMovieTicketPriceText.text =
            binding.bookedMovieTicketPriceText.context.getString(
                R.string.total_price,
                bookingStatus.calculateTicketPrices()
            )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun bookingStatus(): BookingStatus {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("bookingStatus", BookingStatus::class.java)
                ?: throw IllegalStateException()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("bookingStatus") as? BookingStatus
                ?: throw IllegalStateException()
        }
    }
}
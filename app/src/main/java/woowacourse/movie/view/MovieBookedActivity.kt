package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MovieBooked
import woowacourse.movie.helper.BuildVersion
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieBookedBinding
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.helper.LocalDateHelper.toDotFormat
import woowacourse.movie.presenter.MovieBookedPresenter

class MovieBookedActivity : AppCompatActivity(), MovieBooked.View {
    private lateinit var binding: MovieBookedBinding
    private lateinit var presenter: MovieBookedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MovieBookedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booked)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = MovieBookedPresenter(this)
        fetchBookingStatus()
    }

    override fun fetchBookingStatus() {
        val bookingStatus = BuildVersion().getParcelableClass(
            intent,
            KEY_BOOKING_STATUS,
            BookingStatus::class
        )
        presenter.loadBookedStatus(bookingStatus)
    }

    override fun showBookedStatus(bookingStatus: BookingStatus) {
        binding.movieTitle.text = bookingStatus.movie.title
        binding.bookingDateTime.text = binding.bookingDateTime.context.getString(
            R.string.movie_running_dateTime,
            bookingStatus.bookedTime.toDotFormat()
        )
        binding.memberCount.text = binding.memberCount.context.getString(
            R.string.member_count,
            bookingStatus.memberCount
        )
        binding.movieTicketPrice.text = binding.movieTicketPrice.context.getString(
            R.string.total_price,
            bookingStatus.calculateTicketPrices()
        )
        val seatsText = bookingStatus.seat.seats.joinToString(", ") { seat ->
            val rowChar = 'A' + seat.row.value
            val colNumber = seat.col.value + 1
            "$rowChar$colNumber"
        }
        binding.bookingSeat.text = seatsText
    }

    companion object {
        private const val KEY_BOOKING_STATUS = "bookingStatus"

        fun movieBookedIntent(
            otherActivity: AppCompatActivity,
            bookingStatus: BookingStatus
        ): Intent {
            return Intent(otherActivity, MovieBookedActivity::class.java)
                .apply { putExtra(KEY_BOOKING_STATUS, bookingStatus) }
        }
    }
}

package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.helper.BuildVersion
import woowacourse.movie.R
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.helper.LocalDateHelper.toDotFormat

class MovieBookedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.movie_booked)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booked)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bookingStatus = bookingStatus()

        val title: TextView = findViewById(R.id.movie_title)
        val bookingDateTime: TextView = findViewById(R.id.booking_date_time)
        val memberCount: TextView = findViewById(R.id.member_count)
        val movieTicketPrice: TextView = findViewById(R.id.movie_ticket_price)

        initBookingStatus(title, bookingStatus, bookingDateTime, memberCount, movieTicketPrice)
    }

    private fun initBookingStatus(
        title: TextView,
        bookingStatus: BookingStatus,
        bookingDateTime: TextView,
        memberCount: TextView,
        movieTicketPrice: TextView
    ) {
        title.text = bookingStatus.movie.title
        bookingDateTime.text = bookingDateTime.context.getString(
            R.string.movie_running_dateTime,
            bookingStatus.bookedTime.toDotFormat()
        )
        memberCount.text = memberCount.context.getString(
            R.string.member_count,
            bookingStatus.memberCount
        )
        movieTicketPrice.text = movieTicketPrice.context.getString(
            R.string.total_price,
            bookingStatus.calculateTicketPrices()
        )
    }

    private fun bookingStatus(): BookingStatus {
        return BuildVersion().getParcelableClass(
            intent,
            KEY_BOOKING_STATUS,
            BookingStatus::class
        )
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

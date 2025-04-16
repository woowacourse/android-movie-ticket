package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.booking_success)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking_success)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieTitle: String = intent.getStringExtra(MOVIE_TITLE_KEY) ?: "해리포터"
        val movieTitleTextView = findViewById<TextView>(R.id.booked_movie_title_text)
        val screeningDateTextView =
            findViewById<TextView>(R.id.booked_movie_running_day_text)
        val memberCountTextView = findViewById<TextView>(R.id.member_count_text)
        val bookedMovieTicketPriceTextView =
            findViewById<TextView>(R.id.booked_movie_ticket_price_text)

        movieTitleTextView.text = movieTitle
        screeningDateTextView.text = screeningDateTextView.context.getString(
            R.string.movie_running_dateTime,
            intent.getStringExtra(MOVIE_SCREENING_DATE_KEY),
            intent.getStringExtra(MOVIE_SCREENING_TIME_KEY)
        )
        memberCountTextView.text = memberCountTextView.context.getString(
            R.string.member_count,
            intent.getStringExtra(MEMBER_COUNT_KEY)?.toIntOrNull() ?: 1
        )
        bookedMovieTicketPriceTextView.text = bookedMovieTicketPriceTextView.context.getString(
            R.string.total_price,
            intent.getStringExtra(TICKET_PRICE_KEY)?.toIntOrNull() ?: 26000
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val MOVIE_TITLE_KEY = "title"
        const val MOVIE_SCREENING_DATE_KEY = "screeningDate"
        const val MOVIE_SCREENING_TIME_KEY = "screeningTime"
        const val MEMBER_COUNT_KEY = "memberCount"
        const val TICKET_PRICE_KEY = "ticketPrice"
    }
}
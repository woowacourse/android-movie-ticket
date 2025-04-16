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
        val screeningDate: String = intent.getStringExtra(MOVIE_SCREENING_DATE_KEY) ?: "2025.04.01"

        val movieTitleTextView = findViewById<TextView>(R.id.booked_movie_title_text)
        val screeningDateTextView =
            findViewById<TextView>(R.id.booked_movie_running_day_text)
        movieTitleTextView.text = movieTitle
        screeningDateTextView.text = screeningDate

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
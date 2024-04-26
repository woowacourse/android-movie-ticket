package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class SeatSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val ticketCount = intent.getIntExtra(TICKET_COUNT, DEFAULT_TICKET_COUNT)
        val screeningDate = intent.getStringExtra(SCREENING_DATE)
        val screeningTime = intent.getStringExtra(SCREENING_TIME)
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET_COUNT = "ticketCount"
        private const val SCREENING_DATE = "screeningDate"
        private const val SCREENING_TIME = "screeningTime"
        private const val DEFAULT_MOVIE_ID = 0
        private const val DEFAULT_TICKET_COUNT = 0

        fun getIntent(
            context: Context,
            movieId: String,
            ticketCount: Int,
            screeningDate: String,
            screeningTime: String,
        ): Intent {
            return Intent(context, SeatSelectActivity::class.java)
                .putExtra(MOVIE_ID, movieId)
                .putExtra(TICKET_COUNT, ticketCount)
                .putExtra(SCREENING_DATE, screeningDate)
                .putExtra(SCREENING_TIME, screeningTime)
        }
    }
}

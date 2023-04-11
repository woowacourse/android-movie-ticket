package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity() {
    var ticketCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movie = intent.getSerializableExtra("movie") as Movie
        val moviePoster = findViewById<ImageView>(R.id.reservation_movie_poster)
        val movieTitle = findViewById<TextView>(R.id.reservation_movie_title)
        val movieReleaseData = findViewById<TextView>(R.id.reservation_movie_release_date)
        val movieRunningTime = findViewById<TextView>(R.id.reservation_movie_running_time)
        val movieSummary = findViewById<TextView>(R.id.reservation_movie_summary)

        moviePoster.setImageResource(movie.poster)
        movieTitle.text = movie.title
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        movieReleaseData.text = "상영일 : ${movie.releaseDate.format(dateTimeFormatter)}"
        movieRunningTime.text = "러닝타임 : ${movie.runningTime}분"
        movieSummary.text = movie.summary

        val decreaseButton = findViewById<TextView>(R.id.reservation_decrease_ticket_button)
        val increaseButton = findViewById<TextView>(R.id.reservation_increase_ticket_button)
        val ticketCountView = findViewById<TextView>(R.id.reservation_ticket_count)
        ticketCountView.text = ticketCount.toString()

        decreaseButton.setOnClickListener {
            ticketCountView.text = (--ticketCount).toString()
        }
        increaseButton.setOnClickListener {
            ticketCountView.text = (++ticketCount).toString()
        }

        val reservationButton = findViewById<TextView>(R.id.reservation_complete_button)
        reservationButton.setOnClickListener {
            val intent = Intent(this, MovieTicketActivity::class.java)
            intent.putExtra("movie", movie)
            intent.putExtra("ticketCount", ticketCount)
            ContextCompat.startActivity(this, intent, null)
        }
    }
}

package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        val movie = intent.getSerializableExtra("movie") as Movie
        val ticketCount = intent.getIntExtra("ticketCount", 1)

        val ticketTitle = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieTitle = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPrice = findViewById<TextView>(R.id.ticket_total_price)

        ticketTitle.text = movie.title
        ticketMovieTitle.text = movie.releaseDate
        ticketCountView.text = "일반 ${ticketCount}명"
        ticketTotalPrice.text = "${(ticketCount * 13000)}원 (현장 결제)"
    }
}

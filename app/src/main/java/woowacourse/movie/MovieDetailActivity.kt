package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra("movie") as Movie
        var numberOfBooker = 1

        val moviePoster = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val description = findViewById<TextView>(R.id.movie_description)
        val minusBtn = findViewById<Button>(R.id.minus_people)
        val booker = findViewById<TextView>(R.id.number_of_people)
        val plusBtn = findViewById<Button>(R.id.plus_people)
        val bookBtn = findViewById<Button>(R.id.book_button)

        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title
        booker.text = numberOfBooker.toString()

        screeningDate.text =
            movie.runningDate.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        runningTime.text = movie.runningTime.toString()
        description.text = movie.description

        minusBtn.setOnClickListener {
            numberOfBooker -= 1
            if (numberOfBooker <= 1) {
                numberOfBooker = 1
            }
            booker.text = numberOfBooker.toString()
        }

        plusBtn.setOnClickListener {
            numberOfBooker += 1
            if (numberOfBooker >= 10) {
                numberOfBooker = 10
            }
            booker.text = numberOfBooker.toString()
        }

        bookBtn.setOnClickListener {
            val ticket =
                Ticket(13000, LocalDateTime.of(2024, 3, 1, 3, 15), movie.title, numberOfBooker)
            val intent = Intent(this, TicketActivity::class.java)
            intent.putExtra("ticket", ticket)
            startActivity(intent)
        }
    }
}

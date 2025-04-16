package woowacourse.movie

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var movie = intent.getSerializableExtra("movie") as? Movie
        if (movie == null) {
            movie =
                Movie(
                    R.drawable.ic_launcher_background,
                    "",
                    Date(
                        LocalDate.of(2025, 1, 1),
                        LocalDate.of(2025, 1, 1),
                    ),
                    "",
                )
        }

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val moviePosterImageView = findViewById<ImageView>(R.id.movie_image)
        val movieTimeTextView = findViewById<TextView>(R.id.movie_time)

        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)
        updateCounterText()

        minusButton.setOnClickListener {
            if (count > 1) {
                count--
            }
            updateCounterText()
        }

        plusButton.setOnClickListener {
            count++
            updateCounterText()
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
        val startDateFormatted = movie.date.startDate.format(formatter)
        val endDateFormatted = movie.date.endDate.format(formatter)

        movieTitleTextView.text = movie.title
        movieDateTextView.text = getString(R.string.movieDate, startDateFormatted, endDateFormatted)
        movieTimeTextView.text = getString(R.string.movieTime, movie.time)
        moviePosterImageView.setImageResource(movie.image)
    }

    private fun updateCounterText() {
        val counterTextView = findViewById<TextView>(R.id.personnel)
        counterTextView.text = count.toString()
    }
}

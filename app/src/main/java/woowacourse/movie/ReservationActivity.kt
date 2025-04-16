package woowacourse.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movie = intent.getSerializableExtra("movie") as? Movie

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val moviePosterImageView = findViewById<ImageView>(R.id.movie_image)
        val movieTimeTextView = findViewById<TextView>(R.id.movie_time)

        movieTitleTextView.text = movie?.title
        movieDateTextView.text = getString(R.string.movieDate, movie?.date)
        movieTimeTextView.text = getString(R.string.movieTime, movie?.time)
        movie?.image?.let { moviePosterImageView.setImageResource(it) }
    }
}

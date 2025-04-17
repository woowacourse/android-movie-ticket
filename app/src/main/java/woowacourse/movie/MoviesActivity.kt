package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupMovies()
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupMovies() {
        val moviesAdapter =
            MoviesAdapter(this, movies) { movie ->
                bookMovie(movie)
            }
        findViewById<ListView>(R.id.lv_movies).adapter = moviesAdapter
    }

    private fun bookMovie(movie: Movie) {
        val intent =
            BookingDetailActivity.newIntent(
                context = this,
                title = movie.title,
                startDate = movie.startDate.toString(),
                endDate = movie.endDate.toString(),
                runningTime = movie.runningTime,
                poster = movie.poster,
            )
        startActivity(intent)
    }
}

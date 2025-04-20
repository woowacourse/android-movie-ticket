package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movieListView: ListView = findViewById(R.id.movies)
        movieListAdapter(movieListView)
    }

    private fun movieListAdapter(movieListView: ListView) {
        val movies = Movies.value
        val movieListAdapter = MovieListAdapter(movies, object : MovieListClick {
            override fun navigateToBook(movie: Movie) {
                val intent = Intent(this@MovieTicketActivity, ReservationActivity::class.java)
                    .apply { putExtra(KEY_MOVIE, movie) }
                startActivity(intent)
            }
        })
        movieListView.adapter = movieListAdapter
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}

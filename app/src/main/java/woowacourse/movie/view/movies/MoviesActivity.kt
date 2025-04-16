package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        setWindowInsets()
        setMovieListView()
    }

    private fun setMovieListView() {
        val lvMovie = findViewById<ListView>(R.id.lv_movie)
        val movies = listOf(
            Movie(
                R.drawable.harrypotter,
                "해리 포터와 마법사의 돌",
                getString(R.string.movie_date, "2025.4.1"),
                getString(R.string.running_time, "152")
            )
        )
        val movieListAdapter = MovieListAdapter(movies, object : OnMovieEventListener {
            override fun onClick(movie: Movie) {

            }
        })
        lvMovie.adapter = movieListAdapter
    }

    private fun setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

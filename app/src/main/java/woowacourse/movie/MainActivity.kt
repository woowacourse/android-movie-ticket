package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var moviesView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        moviesView = findViewById(R.id.lv_movies)

        val movies: List<Movie> = DefaultMovies.movies

        val adapter =
            MoviesAdapter(movies) { movie ->
                val intent = Intent(this, ReserveActivity::class.java)
                intent.putExtra(getString(R.string.key_movie), movie)
                startActivity(intent)
            }
        moviesView.adapter = adapter
    }
}

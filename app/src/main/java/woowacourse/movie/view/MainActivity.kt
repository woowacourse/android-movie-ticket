package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.adapter.MovieAdapter
import woowacourse.movie.view.reservation.ReservationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movies: List<Movie> = Movie.dummy
        val listView: ListView = findViewById(R.id.list_view)
        val movieAdapter: MovieAdapter =
            MovieAdapter(movies) { movie ->
                val intent = Intent(this, ReservationActivity::class.java)
                intent.putExtra(KEY_MOVIE, movie)
                startActivity(intent)
            }

        listView.adapter = movieAdapter
    }

    companion object {
        private const val KEY_MOVIE = "movie"
    }
}

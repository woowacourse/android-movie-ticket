package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.adapter.MovieAdapter
import java.time.LocalDate

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

        val movies: List<Movie> = initMovie()
        val listView: ListView = findViewById(R.id.list_view)
        val movieAdapter: MovieAdapter =
            MovieAdapter(movies) { movie ->
                val intent = Intent(this, ReservationActivity::class.java)
                intent.putExtra(KEY_MOVIE, movie)
                startActivity(intent)
            }

        listView.adapter = movieAdapter
    }

    private fun initMovie(): List<Movie> {
        return listOf(
            Movie(
                R.drawable.harry,
                "해리 포터와 마법사의 돌",
                Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                152,
            ),
        )
    }

    companion object {
        private const val KEY_MOVIE = "movie"
    }
}

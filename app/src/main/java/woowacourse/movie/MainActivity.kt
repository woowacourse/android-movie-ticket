package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.domain.Movie
import java.time.Duration
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var allItems: List<Movie>
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allItems =
            listOf(
                Movie(
                    "해리 포터와 마법사의 돌",
                    LocalDate.of(2025,4,1),
                    LocalDate.of(2025,4,25),
                    Duration.ofMinutes(152),
                ),
            )

        val listView: ListView = findViewById(R.id.movie_list)
        adapter = MovieListAdapter(allItems) { movie ->
            val intent = Intent(this, BookingActivity::class.java).apply {
                putExtra("MOVIE_INFO", movie)
            }
            startActivity(intent)
        }

        listView.adapter = adapter
    }
}

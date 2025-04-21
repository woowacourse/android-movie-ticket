package woowacourse.movie

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
        adapter = MovieListAdapter(this, allItems)
        listView.adapter = adapter
    }
}

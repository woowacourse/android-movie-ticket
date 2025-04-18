package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
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

        val movies: List<Movie> =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                    poster = R.drawable.harry_potter_poster,
                ),
            )

        val movieListView = findViewById<ListView>(R.id.movie_list)
        val movieAdapter = MovieAdapter(movies)
        movieListView.adapter = movieAdapter
    }
}

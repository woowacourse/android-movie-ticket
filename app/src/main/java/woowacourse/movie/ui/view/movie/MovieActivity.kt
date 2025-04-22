package woowacourse.movie.ui.view.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.util.Keys
import java.time.LocalDate

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie)
        applySystemBarInsets()

        val movies = loadMoviesOrNull() ?: mockMovies()
        val movieAdapter = MovieAdapter(movieList = movies)
        val listView = findViewById<ListView>(R.id.listview_layout)

        listView.adapter = movieAdapter
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadMoviesOrNull(): List<Movie>? {
        return IntentCompat.getParcelableArrayListExtra(
            intent,
            Keys.Extra.LOADED_MOVIE_ITEMS,
            Movie::class.java,
        )?.toList()
    }

    private fun mockMovies(): List<Movie> {
        return listOf(
            Movie(
                imageSource = R.drawable.harry_potter,
                title = "해리 포터와 마법사의 돌",
                runningTime = 152,
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
            ),
            Movie(
                imageSource = R.drawable.harry_potter,
                title = "해리포터 시리즈 2",
                runningTime = 151,
                screeningStartDate = LocalDate.of(2025, 4, 21),
                screeningEndDate = LocalDate.of(2025, 5, 10),
            ),
        )
    }
}

package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        applySystemBarInsets()

        val intentMovieData =
            movieOrNull() ?: mockData()

        val movieList = listOf(intentMovieData)
        val movieAdapter = MovieAdapter(this, movieList)
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

    private fun movieOrNull(): Movie? {
        return IntentCompat.getParcelableExtra(intent, "movieData", Movie::class.java)
    }

    private fun mockData(): Movie {
        return Movie(
            imageSource = R.drawable.harry_potter,
            title = "해리 포터와 마법사의 돌",
            runningTime = 152,
            screeningStartDate = LocalDate.of(2025, 4, 1),
            screeningEndDate = LocalDate.of(2025, 4, 25),
        )
    }
}

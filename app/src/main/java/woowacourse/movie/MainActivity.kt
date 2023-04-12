package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = Movies(
            listOf(
                Movie(
                    R.drawable.poster_harrypotter,
                    "해리 포터",
                    LocalDateTime.of(2020, 1, 10, 9, 0),
                    153,
                    "adsfasdfadsf",
                ),
            ),
        )
        val movieList = findViewById<ListView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(this, movies)
    }
}

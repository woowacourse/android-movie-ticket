package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movieList = ArrayList<Movie>()
        movieList.add(
            Movie(
                "해리포터와 마법사의 돌",
                listOf(LocalDate.of(2024, 3, 1)),
                152,
                "",
                R.drawable.harry_image1.toString()
            )
        )

        val movieAdapter = MovieListAdapter(this, movieList)

        val listView = findViewById<ListView>(R.id.movie_list)

        listView.adapter = movieAdapter
    }
}

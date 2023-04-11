package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MovieListActivity : AppCompatActivity() {
    val movieList = arrayListOf<Movie>(
        Movie(
            title = "해리 포터와 마법사의 돌",
            releaseDate = "상영일: 2024.3.1",
            runningTime = "러닝타임: 152분",
            poster = R.drawable.ic_launcher_background,
        ),
        Movie(
            title = "해리 포터와 비밀의 방",
            releaseDate = "상영일: 2024.3.1",
            runningTime = "러닝타임: 152분",
            poster = R.drawable.ic_launcher_background,
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movieListView = findViewById<ListView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(movieList)
    }
}

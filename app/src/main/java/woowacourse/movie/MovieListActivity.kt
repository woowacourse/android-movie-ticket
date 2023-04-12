package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    private val movieList = arrayListOf(
        Movie(
            title = "해리 포터와 마법사의 돌",
            releaseDate = LocalDate.of(2021, 3, 1),
            runningTime = 152,
            summary = "해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을해리 포으터와 마법사의 도을",
            poster = R.drawable.ic_launcher_background,
        ),
        Movie(
            title = "인셉션",
            releaseDate = LocalDate.of(2021, 3, 1),
            runningTime = 112,
            summary = "시간과 정신의 방",
            poster = R.drawable.ic_launcher_foreground,
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMovieList()
    }

    private fun initMovieList() {
        val movieListView = findViewById<ListView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(this, movieList)
    }
}

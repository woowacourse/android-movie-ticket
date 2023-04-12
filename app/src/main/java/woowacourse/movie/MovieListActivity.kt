package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    private val Cinema: Cinema = Cinema(
        listOf(
            MovieInfo(
                movie = Movie(
                    title = "해리 포터와 마법사의 돌",
                    runningTime = 152,
                    summary = "asdasdasdasdasdasdasd",
                    poster = R.drawable.ic_launcher_background,
                ),
                screeningDate = ScreeningDate(
                    startDate = LocalDate.of(2021, 3, 1),
                    endDate = LocalDate.of(2021, 3, 31),
                ),
            ),
            MovieInfo(
                movie = Movie(
                    title = "해리 포터와 비밀의 방",
                    runningTime = 112,
                    summary = "asdasdasdasdasdasdasd",
                    poster = R.drawable.ic_launcher_foreground,
                ),
                screeningDate = ScreeningDate(
                    startDate = LocalDate.of(2021, 3, 1),
                    endDate = LocalDate.of(2021, 3, 31),
                ),
            ),
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMovieList()
    }

    private fun initMovieList() {
        val movieListView = findViewById<ListView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(this, Cinema)
    }
}

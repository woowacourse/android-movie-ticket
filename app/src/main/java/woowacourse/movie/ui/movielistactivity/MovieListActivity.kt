package woowacourse.movie.ui.movielistactivity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R

class MovieListActivity : AppCompatActivity() {

    lateinit var movieListAdapter: MovieListAdapter
    lateinit var movieListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initViewId()
        initMovieListAdapter()
        initMovieListView()
    }

    private fun initViewId() {
        movieListView = findViewById(R.id.lv_movie)
    }

    private fun initMovieListAdapter() {
        val tempMovies = listOf<MovieData>(
            MovieData(
                posterImage = R.drawable.harrypotter_poster,
                title = "해리 포터와 마법사의 돌",
                screeningDay = "상영일: 2024.3.1",
                runningTime = 152,
                description = this.getString(R.string.dummy_data)
            )
        )
        movieListAdapter = MovieListAdapter(this, tempMovies)
    }

    private fun initMovieListView() {
        movieListView.adapter = movieListAdapter
    }
}

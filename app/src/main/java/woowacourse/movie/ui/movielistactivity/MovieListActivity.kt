package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningPeriodState
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity
import java.time.LocalDate

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
        val tempMovies = listOf<MovieDataState>(
            MovieDataState(
                posterImage = R.drawable.harrypotter_poster,
                title = "해리 포터와 마법사의 돌",
                screeningDay = ScreeningPeriodState(
                    LocalDate.parse("2023-04-01"),
                    LocalDate.parse("2023-04-28")
                ),
                runningTime = 152,
                description = this.getString(R.string.dummy_data)
            )
        )
        movieListAdapter = MovieListAdapter(this, tempMovies, ::onButtonClickListener)
    }

    private fun initMovieListView() {
        movieListView.adapter = movieListAdapter
    }

    private fun onButtonClickListener(item: MovieDataState) {
        val intent = Intent(this, MovieBookingActivity::class.java).putExtra(
            "movieData",
            item
        )
        startActivity(intent)
    }
}

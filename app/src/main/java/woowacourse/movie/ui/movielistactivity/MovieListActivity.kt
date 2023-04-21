package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningPeriodState
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {

    lateinit var movieDataAdapter: MovieDataAdapter
    lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initViewId()
        initMovieListAdapter()
        initMovieListView()
    }

    private fun initViewId() {
        movieRecyclerView = findViewById(R.id.rv_movie)
    }

    private fun initMovieListAdapter() {
        val tempMovies = List(100) { index ->
            MovieDataState(
                posterImage = R.drawable.harrypotter_poster,
                title = "해리 포터와 마법사의 돌 $index",
                screeningDay = ScreeningPeriodState(
                    LocalDate.parse("2023-04-01"),
                    LocalDate.parse("2023-04-28")
                ),
                runningTime = 152,
                description = getString(R.string.dummy_data)
            )
        }
        movieDataAdapter = MovieDataAdapter(this, tempMovies, ::onButtonClickListener)
    }

    private fun initMovieListView() {
        movieRecyclerView.adapter = movieDataAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onButtonClickListener(item: MovieDataState) {
        val intent = Intent(this, MovieBookingActivity::class.java).putExtra(
            MOVIE_DATA,
            item
        )
        startActivity(intent)
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
    }
}

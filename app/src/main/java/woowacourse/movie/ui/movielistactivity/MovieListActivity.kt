package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.net.Uri
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

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieRecyclerView: RecyclerView

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
        val tempMovies = List(10) { index ->
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
        val advertisementImage = R.drawable.img_ad
        movieListAdapter = MovieListAdapter(tempMovies, advertisementImage, ::onMovieClickListener, ::onAdvertisementClickListener)
    }

    private fun onMovieClickListener(item: MovieDataState) {
        val intent = Intent(this, MovieBookingActivity::class.java)
            .putExtra(MOVIE_DATA, item)
        startActivity(intent)
    }

    private fun onAdvertisementClickListener() {
        val url = "https://www.woowahan.com/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun initMovieListView() {
        movieRecyclerView.adapter = movieListAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
    }
}

package woowacourse.movie.ui.movielistactivity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.model.MovieUIModel.Companion.movieToMovieUiModel
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
        // temp Movies 는 서버 어딘가에서 받아오는 것이라 가정
        val tempMovies = List(1000) {
            Movie(
                title = "해리 포터와 마법사의 돌$it",
                screeningDay = ScreeningPeriod(
                    LocalDate.parse("2023-04-01"),
                    LocalDate.parse("2023-04-28")
                ),
                runningTime = 152,
                description = this.getString(R.string.dummy_data)
            )
        }

        val movieUIModels = tempMovies.map { movie: Movie -> movie.movieToMovieUiModel() }
        movieListAdapter = MovieListAdapter(movieUIModels)
    }

    private fun initMovieListView() {
        movieListView.adapter = movieListAdapter
    }
}

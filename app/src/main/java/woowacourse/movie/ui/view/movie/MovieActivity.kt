package woowacourse.movie.ui.view.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster
import woowacourse.movie.util.mapper.MovieModelMapper

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie)
        applySystemBarInsets()

        val movies: List<MovieUiModel> = loadMoviesOrNull() ?: mockMovies()
        val movieAdapter = MovieAdapter(movieList = validatedMovies(movies))
        val listView = findViewById<ListView>(R.id.listview_layout)

        listView.adapter = movieAdapter
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadMoviesOrNull(): List<MovieUiModel>? {
        val movieUiModels =
            IntentCompat.getParcelableArrayListExtra(
                intent,
                EXTRA_LOADED_MOVIE_ITEMS,
                MovieUiModel::class.java,
            )?.toList()
        return movieUiModels
    }

    private fun mockMovies(): List<MovieUiModel> {
        return listOf(
            MovieUiModel(
                id = 1L,
                poster = Poster.Resource(R.drawable.harry_potter),
                title = "해리 포터와 마법사의 돌",
                runningTime = "152",
                screeningStartDate = "2025.4.1",
                screeningEndDate = "2025.4.25",
            ),
            MovieUiModel(
                id = 2L,
                poster = Poster.Resource(R.drawable.harry_potter),
                title = "해리포터 시리즈 2",
                runningTime = "151",
                screeningStartDate = "2025.4.21",
                screeningEndDate = "2025.5.10",
            ),
        )
    }

    private fun validatedMovies(movieUiModels: List<MovieUiModel>): List<Movie> {
        return movieUiModels.map { uiModel -> MovieModelMapper.toDomain(uiModel) }
    }

    companion object {
        private const val EXTRA_LOADED_MOVIE_ITEMS = "extra_loaded_movie_items"

        fun newIntent(
            context: Context,
            movieUiModels: ArrayList<MovieUiModel>,
        ): Intent {
            return Intent(
                context,
                MovieActivity::class.java,
            ).apply { putParcelableArrayListExtra(EXTRA_LOADED_MOVIE_ITEMS, movieUiModels) }
        }
    }
}

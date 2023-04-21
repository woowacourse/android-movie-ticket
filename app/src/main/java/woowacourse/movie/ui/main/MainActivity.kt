package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import woowacourse.movie.ui.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieListView: RecyclerView by lazy { findViewById(R.id.listView) }
    private val adapter: MainListAdapter by lazy {
        MainListAdapter(
            MovieRepositoryImpl.allMovies()
                .map {
                    MovieItemModel(
                        it,
                        { navigateMovieDetail((adapter.items[it] as MovieItemModel).movieState) }
                    )
                }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListView.adapter = adapter
    }

    private fun navigateMovieDetail(movie: MovieState) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        startActivity(intent)
    }

    companion object {
        internal const val KEY_MOVIE = "key_movie"
    }
}

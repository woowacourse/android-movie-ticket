package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.AdbRepository
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.AdbState
import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.adb.AdbDetailActivity
import woowacourse.movie.ui.main.itemModel.AdbItemModel
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import woowacourse.movie.ui.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieListView: RecyclerView by lazy { findViewById(R.id.listView) }
    private val adapter: MainListAdapter by lazy {
        MainListAdapter(
            MovieRepository.allMovies()
                .map {
                    MovieItemModel(
                        it,
                        { navigateMovieDetail((adapter.items[it] as MovieItemModel).movieState) }
                    )
                },
            AdbRepository.allAdb().map {
                AdbItemModel(
                    it,
                    {}
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

    private fun navigateAdb(adbState: AdbState) {
        val intent = Intent(this, AdbDetailActivity::class.java)
        intent.putExtra(KEY_ADB, adbState)
        startActivity(intent)
    }

    companion object {
        internal const val KEY_MOVIE = "key_movie"
        internal const val KEY_ADB = "key_adb"
    }
}

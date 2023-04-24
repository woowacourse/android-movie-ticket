package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.AdvRepository
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.AdvState
import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.adv.AdvDetailActivity
import woowacourse.movie.ui.main.itemModel.AdvItemModel
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import woowacourse.movie.ui.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieListView: RecyclerView by lazy { findViewById(R.id.rv_main) }
    private val adapter: MainPageAdapter by lazy {
        MainPageAdapter(
            movie = MovieRepository.allMovies().map {
                it.convertToItemModel { position ->
                    navigateMovieDetail((adapter.items[position] as MovieItemModel).movieState)
                }
            },
            adv = AdvRepository.allAdv().map {
                it.convertToItemModel { position ->
                    navigateAdbDetail((adapter.items[position] as AdvItemModel).advState)
                }
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

    private fun navigateAdbDetail(adbState: AdvState) {
        val intent = Intent(this, AdvDetailActivity::class.java)
        intent.putExtra(KEY_ADV, adbState)
        startActivity(intent)
    }

    companion object {
        internal const val KEY_MOVIE = "key_movie"
        internal const val KEY_ADV = "key_adb"
    }
}

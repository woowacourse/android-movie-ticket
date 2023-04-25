package woowacourse.movie.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.databinding.ActivityMoviesBinding
import woowacourse.movie.item.AdvertisingItem
import woowacourse.movie.item.ModelItem
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.toMovieItem
import woowacourse.movie.util.Mock

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMoviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        val movieListView: LinearLayout = binding.root
        setContentView(movieListView)

        initMovieListView(binding.moviesListView)
    }

    private fun initMovieListView(movieListView: RecyclerView) {
        val movieModels: MutableList<ModelItem> =
            Mock.getMovieModels().map(MovieModel::toMovieItem).toMutableList()

        var position = 0
        while (position < movieModels.size) {
            if ((position + 1) % 4 == 0) movieModels.add(position, AdvertisingItem())
            position++
        }

        movieListView.adapter = MoviesAdapter(movieModels)
    }
}

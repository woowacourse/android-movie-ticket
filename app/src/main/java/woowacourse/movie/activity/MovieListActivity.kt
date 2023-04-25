package woowacourse.movie.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.data.MockDataRepository
import woowacourse.movie.model.MovieListItem

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MovieListAdapter(
            getMovieListData()
        ) { item ->
            when (item) {
                is MovieListItem.MovieModel -> {
                    val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_KEY, item)
                    this@MovieListActivity.startActivity(intent)
                }
                is MovieListItem.AdModel -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                    this@MovieListActivity.startActivity(intent)
                }
            }
        }
        recyclerView.adapter = adapter
    }

    private fun getMovieListData(): List<MovieListItem> {
        val movies = MockDataRepository.getData(MovieListItem.MovieModel::class)
        val ads = MockDataRepository.getData(MovieListItem.AdModel::class)
        var adIndex = 0
        return movies.flatMapIndexed { index, movie ->
            if (index % AD_CYCLE == AD_CYCLE - 1) listOf(
                movie,
                ads[adIndex++ % ads.size]
            ) else listOf(movie)
        }
    }

    companion object {
        private const val AD_CYCLE = 3
        private const val MOVIE_KEY = "movie"
    }
}

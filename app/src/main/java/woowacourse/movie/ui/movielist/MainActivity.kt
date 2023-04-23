package woowacourse.movie.ui.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.ui.movielist.adapter.ItemClickListener
import woowacourse.movie.ui.movielist.adapter.MovieListAdapter
import woowacourse.movie.utils.MockData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieItems = MockData.getMoviesWithAds()
        setMovieList(movieItems)
    }

    private fun setMovieList(movies: List<MovieListModel>) {
        val moviesView = findViewById<RecyclerView>(R.id.main_movie_list)
        moviesView.layoutManager = LinearLayoutManager(this)
        moviesView.adapter = MovieListAdapter(
            movies,
            object : ItemClickListener {
                override fun onMovieItemClick(movie: MovieListModel.MovieModel) {
                    moveToDetailActivity(movie)
                }

                override fun onAdItemClick(ad: MovieListModel.AdModel) {
                    moveToWebPage(ad)
                }
            }
        )
    }

    private fun moveToDetailActivity(movie: MovieListModel.MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        startActivity(intent)
    }

    private fun moveToWebPage(ad: MovieListModel.AdModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}

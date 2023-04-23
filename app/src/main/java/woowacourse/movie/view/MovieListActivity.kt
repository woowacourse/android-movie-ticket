package woowacourse.movie.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.repository.MovieMockRepository
import woowacourse.movie.view.mapper.toUiModel
import woowacourse.movie.view.model.MovieListModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieMockRepository.findAll()
        val dataList = generateMovieListData(movies)

        val movieAdapter = MovieListAdapter(
            dataList = dataList,
            onItemClick = object : MovieListAdapter.OnItemClick {
                override fun onMovieClick(movie: MovieListModel.MovieUiModel) {
                    val intent = ReservationActivity.newIntent(this@MovieListActivity, movie)
                    startActivity(intent)
                }

                override fun onAdClick(ad: MovieListModel.MovieAdModel) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
                    startActivity(intent)
                }
            }
        )
        val movieListView = findViewById<RecyclerView>(R.id.movie_recyclerview)
        movieListView.adapter = movieAdapter
    }

    private fun generateMovieListData(movies: List<Movie>): List<MovieListModel> {
        val dataList = mutableListOf<MovieListModel>()
        val ad = MovieListModel.MovieAdModel(
            R.drawable.woowacourse_banner,
            "https://woowacourse.github.io/"
        )
        movies.forEachIndexed { index, movie ->
            if (index % AD_POST_INTERVAL == AD_POST_INTERVAL - 1) {
                dataList.add(movie.toUiModel())
                dataList.add(ad)
                return@forEachIndexed
            }
            dataList.add(movie.toUiModel())
        }
        return dataList
    }

    companion object {
        private const val AD_POST_INTERVAL = 3
    }
}

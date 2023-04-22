package woowacourse.movie.view.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.moviedetail.MovieDetailActivity
import woowacourse.movie.view.viewmodel.DummyData
import woowacourse.movie.view.viewmodel.MovieListData
import woowacourse.movie.view.viewmodel.MovieListData.ADData
import woowacourse.movie.view.viewmodel.MovieUIModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setUpMovieDatas()
    }

    private fun setMovieData(): List<MovieListData> = DummyData.getItems()

    private fun setUpMovieDatas() {

        val movieListView = findViewById<RecyclerView>(R.id.movie_recyclerView)
        val movieAdapter = MovieRecyclerAdapter(
            setMovieData(),
            object : MovieRecyclerAdapter.OnClickItem {
                override fun onClick(movie: MovieUIModel) {
                    val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_KEY, movie)
                    startActivity(intent)
                }
            },
            ::clickAdvertisement
        )

        movieListView.layoutManager = LinearLayoutManager(this)
        movieListView.adapter = movieAdapter
    }

    private fun clickAdvertisement(ad: ADData) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
        startActivity(intent)
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}

package woowacourse.movie.view.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.data.MovieData
import woowacourse.movie.domain.data.MovieDummyData
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.view.moviedetail.MovieDetailActivity
import woowacourse.movie.view.viewmodel.toUIModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setUpMovieDatas(MovieDummyData)
    }

    private fun setMovieData(movieData: MovieData): List<Movie> = movieData.getAll()

    private fun setUpMovieDatas(movieData: MovieData) {
        val movieListView = findViewById<ListView>(R.id.movie_listView)
        val movieListViewAdapter = MovieListViewAdapter(
            setMovieData(movieData),
            object : MovieListViewAdapter.OnBookListener {
                override fun onClick(movie: Movie) {
                    val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_KEY, movie.toUIModel())
                    startActivity(intent)
                }
            }
        )

        movieListView.adapter = movieListViewAdapter
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}

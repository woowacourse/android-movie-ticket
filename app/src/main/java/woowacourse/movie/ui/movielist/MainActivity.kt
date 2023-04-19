package woowacourse.movie.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.mapper.toModel
import woowacourse.movie.ui.const.KEY_MOVIE
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.utils.MockData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = MockData.getMovies()
        setMovieList(movies)
    }

    private fun setMovieList(movies: List<Movie>) {
        val moviesView = findViewById<ListView>(R.id.main_movie_list)
        moviesView.adapter = MovieListAdapter(
            movies,
            object : MovieListAdapter.ItemButtonClickListener {
                override fun onClick(position: Int) {
                    moveToDetailActivity(movies[position])
                }
            }
        )
        moviesView.setOnItemClickListener { parent, view, position, id ->
            moveToDetailActivity(movies[position])
        }
    }

    private fun moveToDetailActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie.toModel())
        startActivity(intent)
    }
}

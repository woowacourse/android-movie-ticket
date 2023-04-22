package woowacourse.movie.ui.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.utils.MockData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = MockData.movies
        setMovieList(movies)
    }

    private fun setMovieList(movies: List<MovieModel>) {
        val moviesView = findViewById<RecyclerView>(R.id.main_movie_list)
        moviesView.layoutManager = LinearLayoutManager(this)
        moviesView.adapter = MovieListAdapter(movies) {
            moveToDetailActivity(movies[it])
        }
    }

    private fun moveToDetailActivity(movie: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}

package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Theater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = Theater().movies
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
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}

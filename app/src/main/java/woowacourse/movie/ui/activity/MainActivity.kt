package woowacourse.movie.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.Movies
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.model.MovieModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = Movies().getAll()
        setMovieList(movies)
    }

    private fun setMovieList(movies: List<MovieModel>) {
        val moviesView = findViewById<RecyclerView>(R.id.main_movie_list)
        moviesView.adapter = MovieAdapter(movies) {
            moveToDetailActivity(it)
        }
    }

    private fun moveToDetailActivity(movie: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}

package woowacourse.movie.activities.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activities.movielist.adapter.MovieListAdapter
import woowacourse.movie.activities.ticketing.TicketingActivity
import woowacourse.movie.model.Movie

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setAdapter()
    }

    private fun setAdapter() {
        val lvMovies: ListView = findViewById(R.id.lv_movies)
        val listAdapter = MovieListAdapter(Movie.provideDummy()) { movie ->
            onBookClick(movie)
        }
        lvMovies.adapter = listAdapter
    }

    private fun onBookClick(item: Movie) {
        val intent = Intent(this@MovieListActivity, TicketingActivity::class.java)
        intent.putExtra(MOVIE_KEY, item)
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie"
    }
}

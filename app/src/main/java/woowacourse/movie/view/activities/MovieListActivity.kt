package woowacourse.movie.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.Movie
import woowacourse.movie.view.adapter.MovieListAdapter
import woowacourse.movie.view.adapter.OnBookClickListener

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val lvMovies: ListView = findViewById(R.id.lv_movies)
        val listAdapter =
            MovieListAdapter(
                Movie.provideDummy(),
                object : OnBookClickListener {
                    override fun onClick(item: Movie) {
                        val intent = Intent(this@MovieListActivity, TicketingActivity::class.java)
                        intent.putExtra(MOVIE_KEY, item)
                        startActivity(intent)
                    }
                }
            )
        lvMovies.adapter = listAdapter
    }

    companion object {
        internal const val MOVIE_KEY = "movie"
    }
}

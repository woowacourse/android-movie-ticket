package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MovieAdapter

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val moviesView = findViewById<ListView>(R.id.lv_movies)
        moviesView.adapter =
            MovieAdapter(MOVIES) {
                Intent(this, TicketingActivity::class.java).apply {
                    putExtra(EXTRA_MOVIE_ID, it)
                    startActivity(this)
                }
            }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }
}

package woowacourse.movie.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.data.Movies
import woowacourse.movie.detailbooking.DetailBookingActivity
import woowacourse.movie.domain.Movie

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_movie_list_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movies: List<Movie> = Movies().getAll()
        val listView: ListView = findViewById(R.id.list_view)
        val movieListAdapter =
            MovieListAdapter(movies) { movie ->
                val intent = Intent(this, DetailBookingActivity::class.java)
                intent.putExtra(Movie.KEY_MOVIE, movie)
                startActivity(intent)
            }

        listView.adapter = movieListAdapter
    }
}

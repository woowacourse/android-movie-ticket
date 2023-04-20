package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import com.example.domain.Movie
import woowacourse.movie.repository.MovieMockRepository
import woowacourse.movie.view.model.toUiModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieMockRepository.findAll()

        val movieAdapter = MovieListAdapter(
            movies,
            object : MovieListAdapter.OnReserveListener {
                override fun onClick(movie: Movie) {
                    val intent = Intent(this@MovieListActivity, ReservationActivity::class.java)
                    intent.putExtra(MOVIE_ITEM, movie.toUiModel())
                    startActivity(intent)
                }
            })
        val movieListView = findViewById<RecyclerView>(R.id.movie_recyclerview)
        movieListView.adapter = movieAdapter
    }

    companion object {
        const val MOVIE_ITEM = "MOVIE_ITEM"
    }
}

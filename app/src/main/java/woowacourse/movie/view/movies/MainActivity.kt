package woowacourse.movie.view.movies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.movies.adapter.MovieAdapter
import woowacourse.movie.view.reservation.detail.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.fetchData()
    }

    override fun showMoviesScreen(
        movies: List<Movie>,
        navigate: (Movie) -> Unit,
    ) {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val movieAdapter: MovieAdapter =
            MovieAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onClickReservation(movie: Movie) {
                        navigate(movie)
                    }
                },
            )
        recyclerView.adapter = movieAdapter
    }

    override fun navigateToReservation(movie: Movie) {
        val intent = ReservationActivity.newIntent(this, movie)
        startActivity(intent)
    }

    companion object {
        fun returnToMain(context: Context): Intent {
            if (context is Activity) {
                context.finish()
            }
            return Intent(context, ReservationActivity::class.java)
        }
    }
}

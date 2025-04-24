package woowacourse.movie.view.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.movie.adapter.MovieAdapter
import woowacourse.movie.view.movie.adapter.MovieListItem
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.MovieReservationActivity

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = MovieListPresenter(this)
        presenter.loadMovieListScreen()
    }

    override fun showMovieList(movies: List<MovieUiModel>) {
        val movieListView: RecyclerView = findViewById(R.id.movie_list)
        movieListView.layoutManager = LinearLayoutManager(this)

        val listWithAds = buildListWithAds(movies)
        val movieAdapter =
            MovieAdapter(listWithAds) { position ->
                when (val item = listWithAds[position]) {
                    is MovieListItem.MovieItem ->
                        startActivity(MovieReservationActivity.newIntent(this, item.movie))

                    is MovieListItem.AdItem -> {}
                }
            }
        movieListView.adapter = movieAdapter
    }

    private fun buildListWithAds(movies: List<MovieUiModel>): List<MovieListItem> {
        val result = mutableListOf<MovieListItem>()

        movies.forEachIndexed { index, movie ->
            result.add(MovieListItem.MovieItem(movie))
            if ((index + 1) % 3 == 0) {
                result.add(MovieListItem.AdItem())
            }
        }

        return result
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MovieListActivity::class.java)
    }
}

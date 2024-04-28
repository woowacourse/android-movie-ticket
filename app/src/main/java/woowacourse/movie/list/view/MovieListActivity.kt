package woowacourse.movie.list.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.list.adapter.MovieAdapter
import woowacourse.movie.list.contract.MovieListContract
import woowacourse.movie.list.model.Advertisement
import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.presenter.MovieListPresenter
import woowacourse.movie.reservation.view.MovieReservationActivity

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    override val presenter = MovieListPresenter(this)
    lateinit var movieIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        movieIntent = Intent(this, MovieReservationActivity::class.java)
        recyclerView = findViewById(R.id.movie_recycler_view)
        presenter.setMoviesInfo()
        presenter.setListViewClickListenerInfo()
    }

    override fun showMoviesInfo(
        movies: List<Movie>,
        advertisements: List<Advertisement>,
    ) {
        movieAdapter = MovieAdapter(movies, advertisements)

        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun setOnListViewClickListener() {
        movieAdapter.setItemClickListener(
            object : MovieAdapter.OnItemClickListener {
                override fun onClick(position: Int) {
                    movieIntent.putExtra(EXTRA_MOVIE_ID_KEY, position.toLong())
                    this@MovieListActivity.startActivity(movieIntent)
                }
            },
        )
    }

    companion object {
        const val EXTRA_MOVIE_ID_KEY = "movie_id_key"
    }
}

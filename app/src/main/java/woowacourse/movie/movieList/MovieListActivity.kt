package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.ErrorHandler
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity
import woowacourse.movie.uiModel.MovieInfoUIModel

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var adapter: MovieListAdapter
    private var presenter: MovieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreated()
    }

    override fun showMovies(items: List<MovieInfoUIModel>) {
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter(items, presenter)
        recyclerView.adapter = adapter
    }

    override fun changeActivity(item: MovieInfoUIModel) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(MOVIE_INFO_KEY, item)
            }
        startActivity(intent)
    }

    override fun showError() {
        ErrorHandler.printError(this)
        finish()
    }

    companion object {
        const val MOVIE_INFO_KEY = "MOVIE_INFO"
    }
}

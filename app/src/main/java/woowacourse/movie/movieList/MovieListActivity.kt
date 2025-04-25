package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.util.ErrorUtils

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var adapter: MovieListAdapter
    private var presenter: MovieListPresenter = MovieListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreated(this)
    }

    override fun showMovie(items: List<MovieInfo>) {
        val listView = findViewById<ListView>(R.id.movie_list)
        adapter = MovieListAdapter(this, items, ::changeActivity, this::showError)
        listView.adapter = adapter
    }

    override fun changeActivity(item: MovieInfo) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra("MOVIE_INFO", item)
            }
        startActivity(intent)
    }

    override fun showError() {
        ErrorUtils.printError(this)
        finish()
    }
}

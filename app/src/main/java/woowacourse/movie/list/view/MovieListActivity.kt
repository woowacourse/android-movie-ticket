package woowacourse.movie.list.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.list.adapter.MovieListAdapter
import woowacourse.movie.list.contract.MovieListContract
import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.presenter.MovieListPresenter
import woowacourse.movie.reservation.view.MovieReservationActivity

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var listView: ListView
    override val presenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        listView = findViewById(R.id.movie_list_view)
        presenter.setMoviesInfo()
        presenter.setListViewClickListenerInfo()
    }

    override fun showMoviesInfo(info: ArrayList<Movie>) {
        listView.adapter = MovieListAdapter(this, info)
    }

    override fun setOnListViewClickListener() {
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, MovieReservationActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID_KEY, position.toLong())
            this.startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID_KEY = "movie_id_key"
    }
}

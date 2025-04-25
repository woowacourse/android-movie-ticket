package woowacourse.movie.view.movies

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movies.MovieListContract.PresenterFactory
import woowacourse.movie.view.movies.adapter.MovieAdapter
import woowacourse.movie.view.movies.model.UiModel

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter: MovieListContract.Presenter by lazy {
        PresenterFactory.providePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.loadUiData()
    }

    override fun showMovieList(movieList: List<UiModel>) {
        val rv = findViewById<RecyclerView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                itemsList = movieList,
                onClickBooking = ::moveToBookingComplete,
            )
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    override fun moveToBookingComplete(movieIdx: Int) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE, movieIdx)
            }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "MOVIE"
    }
}

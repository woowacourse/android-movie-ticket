package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.MovieListPresenter
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val movies: ListView = findViewById(R.id.lv_movies)
        movies.adapter = presenter.getAdapter()
    }

    override fun navigateToTicketing(screeningId: Long) {
        Intent(this, TicketingActivity::class.java).apply {
            putExtra(EXTRA_SCREENING_ID, screeningId)
            startActivity(this)
        }
    }

    companion object {
        const val EXTRA_SCREENING_ID = "screening_id"
    }
}

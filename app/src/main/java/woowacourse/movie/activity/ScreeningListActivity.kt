package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.presenter.ScreeningListPresenter

class ScreeningListActivity : AppCompatActivity(), ScreeningListContract.View {
    private lateinit var moviesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val movieAdapter = MovieAdapter()
        ScreeningListPresenter(
            screeningListView = this,
            movieAdapter = movieAdapter,
        )
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = movieAdapter
    }

    override fun navigateToMovieDetail(screening: Screening) {
        val intent =
            Intent(this, ScreeningDetailActivity::class.java).apply {
                putExtra("Screening", screening)
            }
        startActivity(intent)
    }
}

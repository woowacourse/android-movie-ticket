package woowacourse.movie.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieData
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.home.adapter.HomeAdapter

class HomeActivity : AppCompatActivity() {
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(::setClickEventOnSelectedMovie) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initHomeAdapter()
    }

    private fun initHomeAdapter() {
        findViewById<RecyclerView>(R.id.rv_main_movie_list).adapter = homeAdapter
        homeAdapter.setData(MovieData.movies)
    }

    private fun setClickEventOnSelectedMovie(movie: Movie) {
        val intent = BookingActivity.getIntent(this, movie.id)
        startActivity(intent)
    }
}

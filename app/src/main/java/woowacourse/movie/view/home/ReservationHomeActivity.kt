package woowacourse.movie.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.db.advertisement.AdvertisementDao
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.presenter.home.ReservationHomeContract
import woowacourse.movie.presenter.home.ReservationHomePresenter
import woowacourse.movie.view.home.adapter.MovieCatalogAdapter
import woowacourse.movie.view.reservation.ReservationDetailActivity

class ReservationHomeActivity : AppCompatActivity(), ReservationHomeContract.View {
    private val presenter = ReservationHomePresenter(this)

    private val movieRecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view_reservation_home) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        initMovieRecyclerView()
    }

    private fun initMovieRecyclerView() {
        val movieCatalogAdapter =
            MovieCatalogAdapter(
                ScreeningDao().findAll(),
                AdvertisementDao().findAll(),
            ) { movie ->
                presenter.loadMovie(movie)
            }
        movieRecyclerView.apply {
            adapter = movieCatalogAdapter
        }
    }

    override fun navigateToDetail(movieId: Int) {
        val intent = Intent(this, ReservationDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_ID = "movieId"
    }
}

package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.AdvertisementMock
import woowacourse.movie.domain.MovieMock
import woowacourse.movie.domain.advertismentPolicy.MovieAdvertisementPolicy
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.data.MovieListViewData
import woowacourse.movie.view.data.MovieListViewType
import woowacourse.movie.view.data.MovieViewData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieRecyclerView()
    }

    private fun makeMovieRecyclerView() {
        val movies = List(2500) { MovieMock.createMovies() }.flatten()
        val advertisementDatas = AdvertisementMock.createAdvertisements()
        val advertisementPolicy = MovieAdvertisementPolicy(3, 1)

        val movieRecyclerView = findViewById<RecyclerView>(R.id.main_movie_list)
        movieRecyclerView.adapter =
            MovieAdapter(
                movies, advertisementDatas, advertisementPolicy,
                ::onClickItem
            )
    }

    private fun onClickItem(data: MovieListViewData) {
        when (data.viewType) {
            MovieListViewType.MOVIE ->
                MovieReservationActivity.from(this, data as MovieViewData).run {
                    startActivity(this)
                }
            MovieListViewType.ADVERTISEMENT -> Unit
        }
    }
}

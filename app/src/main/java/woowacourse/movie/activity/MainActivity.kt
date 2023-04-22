package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Advertisement
import woowacourse.movie.domain.AdvertisementMock
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieMock
import woowacourse.movie.domain.advertismentPolicy.AdvertisementPolicy
import woowacourse.movie.domain.advertismentPolicy.MovieAdvertisementPolicy
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.data.MovieListViewData
import woowacourse.movie.view.data.MovieListViewType
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.MovieViewDatas
import woowacourse.movie.view.mapper.AdvertisementMapper.toView
import woowacourse.movie.view.mapper.MovieMapper.toView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieList()
    }

    private fun makeMovieList() {
        val movies = List(2500) { MovieMock.createMovies() }.flatten()
        val advertisementDatas = AdvertisementMock.createAdvertisements()
        val advertisementPolicy = MovieAdvertisementPolicy(3, 1)

        val movieList = findViewById<RecyclerView>(R.id.main_movie_list)
        movieList.adapter =
            MovieAdapter(
                makeMovieListViewData(movies, advertisementDatas, advertisementPolicy),
                ::onClickItem
            )
    }

    private fun onClickItem(data: MovieListViewData) {
        when (data.viewType) {
            MovieListViewType.MOVIE ->
                MovieReservationActivity.from(this, data as MovieViewData).run {
                    startActivity(this)
                }
            MovieListViewType.ADVERTISEMENT -> {
            }
        }
    }

    private fun makeMovieListViewData(
        movie: List<Movie>,
        advertisement: List<Advertisement>,
        advertisementPolicy: AdvertisementPolicy
    ): MovieViewDatas {
        val result = mutableListOf<MovieListViewData>()
        for (index in movie.indices) {
            if (index > 0 && index % advertisementPolicy.movieCount == 0)
                result.add(advertisement[0].toView())
            result.add(movie[index].toView())
        }
        return MovieViewDatas(result)
    }
}

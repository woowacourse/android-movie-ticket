package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.MovieMock
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.data.AdvertisementViewData
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.MovieViewDatas
import woowacourse.movie.view.mapper.MovieMapper.toView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieList()
    }

    private fun makeMovieList() {
        val movieViewDatas = MovieViewDatas(
            listOf(
                MovieMock.create().toView(),
                MovieMock.create().toView(),
                MovieMock.create().toView(),
                AdvertisementViewData(),
                MovieMock.create().toView(),
                MovieMock.create().toView(),
                MovieMock.create().toView(),
            )
        )
        val movieList = findViewById<RecyclerView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(movieViewDatas)
    }

    private fun reserveMovie(movie: MovieViewData) {
        MovieReservationActivity.from(this, movie).run {
            startActivity(this)
        }
    }
}

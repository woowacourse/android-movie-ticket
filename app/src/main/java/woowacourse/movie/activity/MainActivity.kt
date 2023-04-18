package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieMock
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.MovieViewData
import woowacourse.movie.view.MovieViewDatas
import woowacourse.movie.view.mapper.MovieMapper.toView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieList()
    }

    private fun makeMovieList() {
        val movieViewDatas = MovieViewDatas(listOf(MovieMock.create().toView()))
        val movieList = findViewById<ListView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(movieViewDatas)
        movieList.setOnItemClickListener { parent, _, position, _ ->
            reserveMovie(parent.getItemAtPosition(position) as MovieViewData)
        }
    }

    private fun reserveMovie(movie: MovieViewData) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MovieViewData.MOVIE_EXTRA_NAME, movie)
        startActivity(intent)
    }
}

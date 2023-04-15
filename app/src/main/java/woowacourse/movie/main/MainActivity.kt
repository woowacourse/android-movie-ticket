package woowacourse.movie.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.entity.RunningTime
import woowacourse.movie.reservation.MovieDetailActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val movieListView: ListView by lazy { findViewById(R.id.listView) }
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(
            initMovieData(),
            object : MovieAdapter.ReservationClickListener {
                override fun onClick(position: Int) {
                    navigateMovieDetail(adapter.movie[position])
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListView.adapter = adapter
    }

    private fun navigateMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        startActivity(intent)
    }

    private fun initMovieData(): List<Movie> {
        return mutableListOf<Movie>().apply {
            add(
                Movie(
                    R.drawable.slamdunk,
                    "더 퍼스트 슬램덩크",
                    LocalDate.of(2023, 1, 4),
                    LocalDate.of(2023, 2, 23),
                    RunningTime(124),
                    "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
                        "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
                        "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다."
                )
            )
        }
    }

    companion object {
        internal const val KEY_MOVIE = "key_movie"
    }
}

package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var moviesListView: ListView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var moviesList: List<MovieInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)


        val movieInfo = MovieInfo(
        Title("차람과 하디의 진지한 여행기"),
        MovieDate(LocalDate.of(2024, 2, 25)) ,
        RunningTime(230),
        Synopsis("wow!")
        )

        val theater = Theater(movieInfo)
        val theaterList = listOf(
            theater
        )
        val moviesList = theaterList.map{
            it.movie
        }
        moviesListView = findViewById(R.id.movies_list_item)
        movieAdapter = MovieAdapter(this, theaterList)
        moviesListView.adapter = movieAdapter



    }
}





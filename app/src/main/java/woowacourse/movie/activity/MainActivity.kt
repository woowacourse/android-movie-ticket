package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_item)

        val movieInfo = MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            LocalDate.of(2024, 2, 25),
            RunningTime(230),
            Synopsis("wow!")
        )
        val theater = Theater(movieInfo)
        findViewById<TextView>(R.id.movie_title).text= "${movieInfo.title}"
        findViewById<TextView>(R.id.movie_release_date).text= "상영일: ${movieInfo.releaseDate}"
        findViewById<TextView>(R.id.movie_duration).text= "${movieInfo.runningTime}분"

        val detailsButton = findViewById<Button>(R.id.movie_details_button)
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        detailsButton.setOnClickListener {
            startActivity(intent)
        }
    }
}





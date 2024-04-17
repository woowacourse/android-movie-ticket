package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_item)

        val movieInfo = MovieInfo(Title("MovieTitle"), Date(4024,2,29),RunningTime(230), Synopsis("wow!"))
        val theater = Theater(movieInfo)
        val detailsButton = findViewById<Button>(R.id.movie_details_button)
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        detailsButton.setOnClickListener {
            startActivity(intent)
        }
    }
}





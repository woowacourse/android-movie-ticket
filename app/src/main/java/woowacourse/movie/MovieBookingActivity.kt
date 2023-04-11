package woowacourse.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.util.customGetParcelableExtra

class MovieBookingActivity : AppCompatActivity() {
    lateinit var movieData: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initExtraData()
        Log.d("링딩동", movieData.toString())
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(-1, "-1", "-1", -1)
        }
    }
}

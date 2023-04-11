package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.util.customGetParcelableExtra

class MovieBookingActivity : AppCompatActivity() {
    lateinit var movieData: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initExtraData()
        initMovieInformation()
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(-1, "-1", "-1", -1)
        }
    }

    private fun initMovieInformation() {
        val ivBookingPoster = findViewById<ImageView>(R.id.iv_booking_poster)
        val tvBookingMovieName = findViewById<TextView>(R.id.tv_booking_movie_name)
        val tvBookingScreeningDay = findViewById<TextView>(R.id.tv_booking_screening_day)
        val tvBookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)
        val tvBookingDescription = findViewById<TextView>(R.id.tv_booking_description)

        ivBookingPoster.setImageResource(movieData.posterImage)
        tvBookingMovieName.text = movieData.title
        tvBookingScreeningDay.text = movieData.screeningDay
        tvBookingRunningTime.text =
            this.getString(R.string.running_time_format).toString().format(movieData.runningTime)
        tvBookingDescription.text = movieData.description
    }
}

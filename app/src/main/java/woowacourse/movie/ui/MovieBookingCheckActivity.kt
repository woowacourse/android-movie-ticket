package woowacourse.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.util.customGetParcelableExtra
import kotlin.properties.Delegates

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    var ticketCount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(R.drawable.img_error, "-1", "-1", -1)
        }
        ticketCount = intent.getIntExtra("ticketCount", -1)
    }
}

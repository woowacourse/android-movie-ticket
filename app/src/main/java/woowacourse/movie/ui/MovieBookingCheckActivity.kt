package woowacourse.movie.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.domain.price.PricePolicy
import woowacourse.movie.domain.price.PricePolicyCalculator
import woowacourse.movie.util.customGetParcelableExtra
import kotlin.properties.Delegates

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    var ticketCount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initMovieInformation(PricePolicyCalculator())
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(R.drawable.img_error, "-1", "-1", -1)
        }
        ticketCount = intent.getIntExtra("ticketCount", -1)
    }

    private fun initMovieInformation(pricePolicy: PricePolicy) {
        val tvBookingCheckMovieName = findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay = findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount = findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney = findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieData.title
        tvBookingCheckScreeningDay.text = movieData.screeningDay
        tvBookingCheckPersonCount.text =
            this.getString(R.string.tv_booking_check_person_count).format(ticketCount)
        tvBookingCheckTotalMoney.text = this.getString(R.string.tv_booking_check_total_money)
            .format(pricePolicy.totalPriceCalculate(13000, ticketCount))
    }
}

package woowacourse.movie.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.price.*
import woowacourse.movie.domain.price.discount.runningpolicy.TimeMovieDayDiscountPolicy
import woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator
import woowacourse.movie.ui.DateTimeFormatters.dateDotTimeColonFormatter
import woowacourse.movie.util.customGetParcelableExtra
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.properties.Delegates

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    var ticketCount by Delegates.notNull<Int>()
    lateinit var bookedScreeningDateTime: ScreeningDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initMovieInformation()
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>("movieData") ?: run {
            finish()
            MovieData(
                R.drawable.img_error,
                "-1",
                ScreeningPeriod(LocalDate.parse("9999-12-30"), LocalDate.parse("9999-12-31")),
                -1
            )
        }
        ticketCount = intent.getIntExtra("ticketCount", -1)
        bookedScreeningDateTime =
            intent.customGetParcelableExtra("bookedScreeningDateTime") ?: run {
                finish()
                ScreeningDateTime(
                    LocalDateTime.parse("9999-12-30T00:00"),
                    ScreeningPeriod(LocalDate.parse("9999-12-30"), LocalDate.parse("9999-12-31"))
                )
            }
    }

    private fun initMovieInformation() {
        val tvBookingCheckMovieName = findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay = findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount = findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney = findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieData.title
        tvBookingCheckScreeningDay.text =
            bookedScreeningDateTime.value.format(dateDotTimeColonFormatter)
        tvBookingCheckPersonCount.text =
            this.getString(R.string.tv_booking_check_person_count).format(ticketCount)
        tvBookingCheckTotalMoney.text = this.getString(R.string.tv_booking_check_total_money)
            .format(applyDisCount(13000, ticketCount).value)
    }

    private fun applyDisCount(ticketPrice: Int, ticketCount: Int): TicketPrice =
        PricePolicyCalculator(TimeMovieDayDiscountPolicy(bookedScreeningDateTime).getDiscountPolicies())
            .totalPriceCalculate(TicketPrice(ticketPrice), TicketCount(ticketCount))
}

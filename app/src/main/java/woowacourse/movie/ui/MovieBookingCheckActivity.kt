package woowacourse.movie.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.*
import woowacourse.movie.domain.price.TicketPrice.Companion.STANDARD_TICKET_PRICE
import woowacourse.movie.domain.price.discount.runningpolicy.TimeMovieDayDiscountPolicy
import woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator
import woowacourse.movie.ui.DateTimeFormatters.dateDotTimeColonFormatter
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.customGetSerializableExtra

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var ticketCount: TicketCount
    lateinit var bookedScreeningDateTime: ScreeningDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initMovieInformation()
    }

    private fun initExtraData() {
        movieData = intent.customGetSerializableExtra(MOVIE_DATA) ?: throw IllegalStateException(INTENT_EXTRA_INITIAL_ERROR)
        ticketCount =
            intent.customGetSerializableExtra(TICKET_COUNT) ?: throw IllegalStateException(INTENT_EXTRA_INITIAL_ERROR)
        bookedScreeningDateTime =
            intent.customGetSerializableExtra(BOOKED_SCREENING_DATE_TIME)
                ?: throw IllegalStateException(INTENT_EXTRA_INITIAL_ERROR)
    }

    private fun initMovieInformation() {
        val tvBookingCheckMovieName = findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay = findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount = findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney = findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieData.title
        tvBookingCheckScreeningDay.text =
            bookedScreeningDateTime.time.format(dateDotTimeColonFormatter)
        tvBookingCheckPersonCount.text =
            this.getString(R.string.tv_booking_check_person_count).format(ticketCount.value)
        tvBookingCheckTotalMoney.text = this.getString(R.string.tv_booking_check_total_money)
            .format(applyDisCount(TicketPrice(STANDARD_TICKET_PRICE), ticketCount).value)
    }

    private fun applyDisCount(ticketPrice: TicketPrice, ticketCount: TicketCount): TicketPrice =
        PricePolicyCalculator(TimeMovieDayDiscountPolicy(bookedScreeningDateTime).getDiscountPolicies())
            .totalPriceCalculate(ticketPrice, ticketCount)

    companion object {
        const val MOVIE_DATA = "movieData"
        const val TICKET_COUNT = "ticketCount"
        const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"

        private const val INTENT_EXTRA_INITIAL_ERROR = "intent 의 데이터 이동시 data가 null으로 넘어오고 있습니다"
    }
}

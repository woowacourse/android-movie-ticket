package woowacourse.movie.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import movie.domain.price.DiscountPolicy
import movie.domain.price.EarlyMorningLateNightDiscount
import movie.domain.price.MovieDayDiscount
import movie.domain.price.PricePolicyCalculator
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.model.mapper.toDomain
import woowacourse.movie.ui.DateTimeFormatters.dateDotTimeColonFormatter
import woowacourse.movie.util.customGetParcelableExtra
import kotlin.properties.Delegates

class MovieBookingCheckActivity : AppCompatActivity() {

    private lateinit var movieDataState: MovieDataState
    private var ticketCount by Delegates.notNull<Int>()
    private lateinit var bookedScreeningDateTimeState: ScreeningDateTimeState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initMovieInformation()
    }

    private fun initExtraData() {
        movieDataState = intent.customGetParcelableExtra<MovieDataState>("movieData", ::finishActivity) ?: return
        ticketCount = intent.getIntExtra("ticketCount", -1)
        bookedScreeningDateTimeState =
            intent.customGetParcelableExtra("bookedScreeningDateTime", ::finishActivity) ?: return
    }

    private fun finishActivity(key: String) {
        Log.d("MovieBookingCheck", "${key}를 찾을 수 없습니다.")
        finish()
    }

    private fun initMovieInformation() {
        val tvBookingCheckMovieName = findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay = findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount = findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney = findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieDataState.title
        tvBookingCheckScreeningDay.text =
            bookedScreeningDateTimeState.dateTime.format(dateDotTimeColonFormatter)
        tvBookingCheckPersonCount.text =
            this.getString(R.string.tv_booking_check_person_count).format(ticketCount)
        tvBookingCheckTotalMoney.text = this.getString(R.string.tv_booking_check_total_money)
            .format(applyDisCount(13000, ticketCount))
    }

    private fun applyDisCount(ticketPrice: Int, ticketCount: Int): Int {
        val discountPolicies = mutableListOf<DiscountPolicy>()
        if (bookedScreeningDateTimeState.toDomain().checkMovieDay()) discountPolicies.add(MovieDayDiscount())
        if (bookedScreeningDateTimeState.toDomain().checkEarlyMorningLateNight()) discountPolicies.add(
            EarlyMorningLateNightDiscount()
        )
        return PricePolicyCalculator(discountPolicies).totalPriceCalculate(ticketPrice, ticketCount)
    }
}

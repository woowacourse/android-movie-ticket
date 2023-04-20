package woowacourse.movie.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
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
        movieDataState = intent.customGetParcelableExtra<MovieDataState>(MOVIE_DATA)
            ?: return finishActivity(MOVIE_DATA)
        ticketCount = intent.getIntExtra(TICKET_COUNT, NULL_POSITION)
        bookedScreeningDateTimeState =
            intent.customGetParcelableExtra(BOOKED_SCREENING_DATE_TIME)
                ?: return finishActivity(BOOKED_SCREENING_DATE_TIME)
    }

    private fun finishActivity(key: String) {
        Log.d(MOIVE_BOOKING_CHECK_LOG_MSG, DATA_NOT_FOUNT_ERROR_MSG.format(key))
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
        tvBookingCheckTotalMoney.text = getString(R.string.tv_booking_check_total_money)
            .format(0)
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
        private const val TICKET_COUNT = "ticketCount"
        private const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
        private const val MOIVE_BOOKING_CHECK_LOG_MSG = "MovieBookingCheck"
        private const val STANDARD_TICKET_PRICE = 13000
        private const val NULL_POSITION = -1
    }
}

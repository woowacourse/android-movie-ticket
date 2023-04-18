package woowacourse.movie.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.service.MovieQueryService
import java.text.DecimalFormat
import java.time.LocalDateTime

class ReservationCompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)

        val reservationInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(ReservationActivity.RESERVATION_INFO, ReservationInfo::class.java)
        } else {
            intent.getSerializableExtra(ReservationActivity.RESERVATION_INFO) as? ReservationInfo
        }
        requireNotNull(reservationInfo) { "인텐트로 받아온 예약 정보 데이터가 널일 수 없습니다." }
        initViewData(reservationInfo.movieId, reservationInfo.screeningDateTime)
    }

    private fun initViewData(movieId: Long, screeningDateTime: LocalDateTime) {
        val reservation = MovieQueryService.getReservation(movieId, screeningDateTime)

        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = reservation.movieTitle
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text =
            reservation.screeningDateTime.format(DATE_TIME_FORMATTER)
        val peopleCountView = findViewById<TextView>(R.id.people_count)
        peopleCountView.text = getString(R.string.reservation_people_count_format)
            .format(getString(R.string.general_person), reservation.audienceCount)
        val totalPriceView = findViewById<TextView>(R.id.total_price)
        totalPriceView.text =
            getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservation.fee))
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}

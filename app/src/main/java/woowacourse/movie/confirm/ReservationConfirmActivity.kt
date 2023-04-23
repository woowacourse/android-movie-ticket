package woowacourse.movie.confirm

import android.os.Bundle
import android.util.Log
import woowacourse.movie.BackKeyActionBarActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_MONEY
import woowacourse.movie.KEY_RESERVATION_SEATS
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.data.MovieAndAd
import woowacourse.movie.databinding.ActivityReservationConfirmBinding
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import woowacourse.movie.entity.Seats
import woowacourse.movie.entity.ViewingDate
import woowacourse.movie.entity.ViewingTime
import woowacourse.movie.utils.getParcelableCompat
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private lateinit var binding: ActivityReservationConfirmBinding

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivityReservationConfirmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val movie = intent.getParcelableCompat<MovieAndAd.Movie>(KEY_MOVIE)!!
        val reservationCount = intent.getParcelableCompat<Count>(KEY_RESERVATION_COUNT)!!
        val totalMoney = intent.getParcelableCompat<Money>(KEY_RESERVATION_MONEY)!!
        val date = intent.getParcelableCompat<ViewingDate>(KEY_RESERVATION_DATE)!!
        val time = intent.getParcelableCompat<ViewingTime>(KEY_RESERVATION_TIME)!!
        val dateTime = LocalDateTime.of(date.value, time.value)
        val seats = intent.getParcelableCompat<Seats>(KEY_RESERVATION_SEATS)!!
        Log.d(LOG_TAG, "$movie , $reservationCount")

        setInitReservationData(movie, dateTime, reservationCount, totalMoney, seats)
    }

    private fun setInitReservationData(
        movie: MovieAndAd.Movie,
        dateTime: LocalDateTime,
        reservationCount: Count,
        totalMoney: Money,
        seats: Seats
    ) {
        binding.reservationTitle.text = movie.title
        binding.reservationDate.text = dateTime.format(DATE_TIME_FORMATTER)
        binding.reservationMoney.text = DECIMAL_FORMATTER.format(totalMoney.value).toString()
        binding.reservationCount.text = reservationCount.value.toString()
        binding.seatsInfo.text = seats.toStringSeats().joinToString(", ")
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        val DECIMAL_FORMATTER = DecimalFormat("#,###")
        private const val LOG_TAG = "mendel and bbotto"
    }
}

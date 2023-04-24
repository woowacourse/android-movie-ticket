package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.domain.usecase.GetMovieRunningDateUseCase
import com.example.domain.usecase.GetMovieRunningTimeUseCase
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.MovieState
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.main.MainActivity.Companion.KEY_MOVIE
import woowacourse.movie.ui.seat.SeatSelectActivity
import woowacourse.movie.util.getParcelableCompat
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.getSerializableCompat
import woowacourse.movie.util.keyError
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDetailActivity : BackKeyActionBarActivity() {
    private val getMovieRunningDateUseCase = GetMovieRunningDateUseCase()
    private val getMovieRunningTimeUseCase = GetMovieRunningTimeUseCase()

    private lateinit var movie: MovieState

    private lateinit var movieInfo: MovieInfo
    private lateinit var dateTimeSpinner: DateTimeSpinner
    private lateinit var reservationCounter: ReservationCounter
    private val reservationConfirm: Button by lazy { findViewById(R.id.reservation_confirm) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        val rootView = window.decorView.rootView
        movie = intent.getParcelableExtraCompat(KEY_MOVIE) ?: return keyError(KEY_MOVIE)
        movieInfo = MovieInfo(rootView).also { it.setMovieState(movie) }
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        } else {
            dateTimeSpinner = DateTimeSpinner(
                rootView,
                movie,
                ::getMovieRunningDates,
                ::getMovieRunningTimes
            )
            reservationCounter = ReservationCounter(rootView)
        }
        reservationConfirm.setOnClickListener { navigateSeatSelectActivity() }
    }

    private fun navigateSeatSelectActivity() {
        val intent = Intent(this, SeatSelectActivity::class.java)
        val dateTime = dateTimeSpinner.getSelectDateTime()
        val reservationState =
            ReservationState(movie, dateTime, reservationCounter.count)
        intent.putExtra(KEY_TICKETS, reservationState)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val dateTime = dateTimeSpinner.getSelectDateTime()
        outState.putSerializable(KEY_DATE, dateTime.toLocalDate())
        outState.putSerializable(KEY_TIME, dateTime.toLocalTime())
        outState.putParcelable(KEY_COUNT, reservationCounter.count)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle) {
        val restoreSelectDate: LocalDate =
            savedInstanceState.getSerializableCompat(KEY_DATE) ?: return keyError(KEY_DATE)
        val restoreSelectTime: LocalTime =
            savedInstanceState.getSerializableCompat(KEY_TIME) ?: return keyError(KEY_TIME)
        val restoreCount: CountState =
            savedInstanceState.getParcelableCompat(KEY_COUNT) ?: return keyError(KEY_COUNT)
        dateTimeSpinner = DateTimeSpinner(
            window.decorView.rootView,
            movie,
            ::getMovieRunningDates,
            ::getMovieRunningTimes,
            LocalDateTime.of(restoreSelectDate, restoreSelectTime)
        )
        reservationCounter = ReservationCounter(window.decorView.rootView, restoreCount)
    }

    private fun getMovieRunningDates(movie: MovieState) =
        getMovieRunningDateUseCase(movie.asDomain())

    private fun getMovieRunningTimes(date: LocalDate) =
        getMovieRunningTimeUseCase(date)

    companion object {
        private const val KEY_COUNT = "key_reservation_count"
        private const val KEY_DATE = "key_reservation_date"
        private const val KEY_TIME = "key_reservation_time"
        internal const val KEY_TICKETS = "key_reservation"
    }
}

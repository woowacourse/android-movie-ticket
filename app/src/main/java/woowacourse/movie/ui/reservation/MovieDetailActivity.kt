package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.domain.usecase.GetMovieRunningDateUseCase
import com.example.domain.usecase.GetMovieRunningTimeUseCase
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.MovieState
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.Toaster
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

    private val image: ImageView by lazy { findViewById(R.id.detail_image) }
    private val title: TextView by lazy { findViewById(R.id.detail_title) }
    private val detailDate: TextView by lazy { findViewById(R.id.detail_date) }
    private val detailTime: TextView by lazy { findViewById(R.id.detail_time) }
    private val description: TextView by lazy { findViewById(R.id.description) }
    private val reservationConfirm: Button by lazy { findViewById(R.id.reservation_confirm) }
    private val minus: Button by lazy { findViewById(R.id.minus) }
    private val plus: Button by lazy { findViewById(R.id.plus) }
    private val countTextView: TextView by lazy { findViewById(R.id.count) }

    private lateinit var movie: MovieState

    private var count: CountState = CountState.of(1)
        set(value) {
            field = value
            countTextView.text = field.value.toString()
        }

    private lateinit var dateTimeSpinner: DateTimeSpinner

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        movie = intent.getParcelableExtraCompat(KEY_MOVIE) ?: return keyError(KEY_MOVIE)
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        } else {
            dateTimeSpinner = DateTimeSpinner(
                window.decorView.rootView,
                movie,
                ::getMovieRunningDates,
                ::getMovieRunningTimes
            )
        }
        initSetOnClickListener()
        initMovieData()
    }

    private fun initMovieData() {
        image.setImageResource(movie.imgId)
        title.text = movie.title
        detailDate.text =
            DateTimeFormatters.convertToDateTildeDate(this, movie.startDate, movie.endDate)
        detailTime.text = getString(R.string.running_time, movie.runningTime)
        description.text = movie.description
    }

    private fun initSetOnClickListener() {
        minus.setOnClickListener {
            if (count.value == 1) {
                Toaster.showToast(this, getString(R.string.error_reservation_min_count))
                return@setOnClickListener
            }
            count -= 1
        }

        plus.setOnClickListener { count += 1 }

        reservationConfirm.setOnClickListener { navigateSeatSelectActivity() }
    }

    private fun navigateSeatSelectActivity() {
        val intent = Intent(this, SeatSelectActivity::class.java)
        val dateTime = dateTimeSpinner.getSelectDateTime()
        val reservationState =
            ReservationState(movie, dateTime, count)
        intent.putExtra(KEY_TICKETS, reservationState)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val dateTime = dateTimeSpinner.getSelectDateTime()
        outState.putSerializable(KEY_DATE, dateTime.toLocalDate())
        outState.putSerializable(KEY_TIME, dateTime.toLocalTime())
        outState.putParcelable(KEY_COUNT, count)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle) {
        val restoreSelectDate: LocalDate =
            savedInstanceState.getSerializableCompat(KEY_DATE) ?: return keyError(KEY_DATE)
        val restoreSelectTime: LocalTime =
            savedInstanceState.getSerializableCompat(KEY_TIME) ?: return keyError(KEY_TIME)
        count = savedInstanceState.getParcelableCompat(KEY_COUNT) ?: return keyError(KEY_COUNT)
        dateTimeSpinner = DateTimeSpinner(
            window.decorView.rootView,
            movie,
            ::getMovieRunningDates,
            ::getMovieRunningTimes,
            LocalDateTime.of(restoreSelectDate, restoreSelectTime)
        )
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

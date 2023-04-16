package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.domain.dateTime.RunningDate
import com.example.domain.dateTime.RunningTime
import com.example.domain.model.Count
import woowacourse.movie.R
import woowacourse.movie.model.MovieRes
import woowacourse.movie.model.ReservationRes
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.Toaster
import woowacourse.movie.ui.confirm.ReservationConfirmActivity
import woowacourse.movie.ui.main.MainActivity.Companion.KEY_MOVIE
import woowacourse.movie.util.customGetSerializable
import woowacourse.movie.util.keyNoExistError
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : BackKeyActionBarActivity() {
    private val image: ImageView by lazy { findViewById(R.id.detail_image) }
    private val title: TextView by lazy { findViewById(R.id.detail_title) }
    private val detailDate: TextView by lazy { findViewById(R.id.detail_date) }
    private val detailTime: TextView by lazy { findViewById(R.id.detail_time) }
    private val description: TextView by lazy { findViewById(R.id.description) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.time_spinner) }
    private val reservationConfirm: Button by lazy { findViewById(R.id.reservation_confirm) }
    private val minus: Button by lazy { findViewById(R.id.minus) }
    private val plus: Button by lazy { findViewById(R.id.plus) }
    private val countTextView: TextView by lazy { findViewById(R.id.count) }

    private val runningDate: RunningDate = RunningDate()
    private val runningTime: RunningTime = RunningTime()
    private lateinit var selectDate: LocalDate
    private lateinit var selectTime: LocalTime
    private lateinit var movie: MovieRes
    private val runningDates: List<LocalDate> by lazy {
        runningDate.getRunningDates(
            movie.startDate,
            movie.endDate
        )
    }
    private lateinit var runningTimes: List<LocalTime>

    private var count: Count = Count(1)
        set(value) {
            field = value
            countTextView.text = field.value.toString()
        }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        movie = intent.customGetSerializable(KEY_MOVIE, ::keyNoExistError) ?: return
        initSetOnClickListener()
        initMovieData()
        setDateSpinnerAdapter()
        initInstanceState()
        savedInstanceState?.let { restoreInstanceState(it) }
        setDateSpinnerListener()
        setTimeSpinnerListener()
    }

    private fun initMovieData() {
        image.setImageResource(movie.imgId)
        title.text = movie.title
        detailDate.text = getString(
            R.string.running_date,
            movie.startDate.format(DATE_TIME_FORMATTER),
            movie.endDate.format(DATE_TIME_FORMATTER)
        )
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

        reservationConfirm.setOnClickListener { navigateReservationConfirm() }
    }

    private fun navigateReservationConfirm() {
        val intent = Intent(this, ReservationConfirmActivity::class.java)
        val reservationRes = ReservationRes.from(movie, LocalDateTime.of(selectDate, selectTime), count)
        intent.putExtra(KEY_RESERVATION, reservationRes)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_DATE, selectDate)
        outState.putSerializable(KEY_TIME, selectTime)
        outState.putInt(KEY_COUNT, count.value)
    }

    private fun setDateSpinnerAdapter() {
        val dateSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningDates.map { it.toString() }
        )
        dateSpinner.adapter = dateSpinnerAdapter
    }

    private fun setTimeSpinnerAdapter() {
        runningTimes = runningTime.getRunningTimes(selectDate)
        val timeSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningTimes.map { it.toString() }
        )
        timeSpinner.adapter = timeSpinnerAdapter
    }

    private fun initInstanceState() {
        selectDate = movie.startDate
        setTimeSpinnerAdapter()
        runningTimes = runningTime.getRunningTimes(selectDate)
        selectTime = runningTimes.first()
    }

    private fun restoreInstanceState(savedInstanceState: Bundle) {
        selectDate = savedInstanceState.customGetSerializable(KEY_DATE, ::keyNoExistError) ?: return
        setTimeSpinnerAdapter()
        selectTime = savedInstanceState.customGetSerializable(KEY_TIME, ::keyNoExistError) ?: return
        runningTimes = RunningTime().getRunningTimes(selectDate)
        dateSpinner.setSelection(runningDates.indexOf(selectDate), false)
        timeSpinner.setSelection(runningTimes.indexOf(selectTime), false)
        count = Count(savedInstanceState.getInt(KEY_COUNT, 1))
    }

    private fun setDateSpinnerListener() {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectDate = runningDates[position]
                setTimeSpinnerAdapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setTimeSpinnerListener() {
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectTime = runningTimes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
        private const val KEY_COUNT = "key_reservation_count"
        private const val KEY_DATE = "key_reservation_date"
        private const val KEY_TIME = "key_reservation_time"
        internal const val KEY_RESERVATION = "key_reservation"
    }
}

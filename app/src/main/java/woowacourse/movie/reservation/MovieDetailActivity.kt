package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.BackKeyActionBarActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.KEY_RESTORE_COUNT
import woowacourse.movie.KEY_RESTORE_DATE
import woowacourse.movie.KEY_RESTORE_TIME
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.Toaster
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.domain.RunningDateSetter
import woowacourse.movie.domain.RunningTimeSetter
import woowacourse.movie.entity.Count
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : BackKeyActionBarActivity() {
    private val image: ImageView by lazy { findViewById(R.id.detail_image) }
    private val title: TextView by lazy { findViewById(R.id.detail_title) }
    private val startDate: TextView by lazy { findViewById(R.id.start_date) }
    private val endDate: TextView by lazy { findViewById(R.id.end_date) }
    private val time: TextView by lazy { findViewById(R.id.detail_time) }
    private val description: TextView by lazy { findViewById(R.id.description) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.time_spinner) }
    private val reservationConfirm: Button by lazy { findViewById(R.id.reservation_confirm) }
    private val minus: Button by lazy { findViewById(R.id.minus) }
    private val plus: Button by lazy { findViewById(R.id.plus) }
    private val count: TextView by lazy { findViewById(R.id.count) }

    private val runningDateSetter: RunningDateSetter = RunningDateSetter()
    private val runningTimeSetter: RunningTimeSetter = RunningTimeSetter()
    private lateinit var selectDate: LocalDate
    private lateinit var selectTime: LocalTime
    private lateinit var movie: Movie
    private val runningDates: List<LocalDate> by lazy {
        runningDateSetter.getRunningDates(
            movie.startDate,
            movie.endDate
        )
    }
    private lateinit var runningTimes: List<LocalTime>

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        movie = intent.customGetSerializable(KEY_MOVIE)!!
        initSetOnClickListener()
        initMovieData()
        setDateSpinnerAdapter()
        initInstanceState()
        savedInstanceState?.let { restoreInstanceState(it) }
        setOnClickDateListener()
        setOnClickTimeListener()
    }

    private fun initMovieData() {
        image.setImageResource(movie.imgResourceId)
        title.text = movie.title
        startDate.text = movie.startDate.format(DATE_TIME_FORMATTER)
        endDate.text = movie.endDate.format(DATE_TIME_FORMATTER)
        time.text = movie.runningTime.value.toString()
        description.text = movie.description
    }

    private fun setDateSpinnerAdapter() {
        val dateSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningDates.map { it.toString() }
        )
        dateSpinner.adapter = dateSpinnerAdapter
    }

    private fun initSetOnClickListener() {
        minus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous--
            if (previous <= 0) {
                Toaster.showToast(this, getString(R.string.error_reservation_min_count))
                return@setOnClickListener
            }
            count.text = previous.toString()
        }

        plus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous++
            count.text = previous.toString()
        }

        reservationConfirm.setOnClickListener {
            val intent = Intent(this, ReservationConfirmActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            intent.putExtra(KEY_RESERVATION_COUNT, Count(count.text.toString().toInt()))
            intent.putExtra(KEY_RESERVATION_DATE, selectDate)
            intent.putExtra(KEY_RESERVATION_TIME, selectTime)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_RESTORE_DATE, selectDate)
        outState.putSerializable(KEY_RESTORE_TIME, selectTime)
        outState.putInt(KEY_RESTORE_COUNT, count.text.toString().toInt())
    }

    fun setTimeSpinnerAdapter() {
        runningTimes = runningTimeSetter.getRunningTimes(selectDate)
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
        runningTimes = runningTimeSetter.getRunningTimes(selectDate)
        selectTime = runningTimes[0]
    }

    private fun restoreInstanceState(savedInstanceState: Bundle) {
        selectDate = savedInstanceState.customGetSerializable(KEY_RESTORE_DATE)!!
        setTimeSpinnerAdapter()
        selectTime = savedInstanceState.customGetSerializable(KEY_RESTORE_TIME)!!
        runningTimes = RunningTimeSetter().getRunningTimes(selectDate)
        dateSpinner.setSelection(runningDates.indexOf(selectDate), false)
        timeSpinner.setSelection(runningTimes.indexOf(selectTime), false)
        count.text = savedInstanceState.getInt(KEY_RESTORE_COUNT).toString()
    }

    private fun setOnClickDateListener() {
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

    private fun setOnClickTimeListener() {
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
    }
}

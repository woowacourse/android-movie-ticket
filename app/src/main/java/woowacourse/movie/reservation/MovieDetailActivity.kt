package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.Toaster
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.domain.RunningDateSetter
import woowacourse.movie.domain.RunningTimeSetter
import woowacourse.movie.entity.Count
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity() {
    private val image: ImageView by lazy { findViewById(R.id.detail_image) }
    private val title: TextView by lazy { findViewById(R.id.detail_title) }
    private val date: TextView by lazy { findViewById(R.id.detail_date) }
    private val time: TextView by lazy { findViewById(R.id.detail_time) }
    private val description: TextView by lazy { findViewById(R.id.description) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.time_spinner) }
    private val reservationConfirm: Button by lazy { findViewById(R.id.reservation_confirm) }
    private val minus: Button by lazy { findViewById(R.id.minus) }
    private val plus: Button by lazy { findViewById(R.id.plus) }
    private val count: TextView by lazy { findViewById(R.id.count) }

    private lateinit var selectDate: LocalDate
    private lateinit var selectTime: LocalTime
    private lateinit var movie: Movie
    private val runningDates: List<LocalDate> by lazy {
        RunningDateSetter().getRunningDates(
            movie.startDate,
            movie.endDate
        )
    }
    private lateinit var runningTimes: List<LocalTime>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movie = intent.getSerializableExtra(KEY_MOVIE) as Movie
        initSetOnClickListener()
        image.setImageResource(movie.imgResourceId)
        initMovieData()

        val dateSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningDates.map { it.toString() }
        )
        dateSpinner.adapter = dateSpinnerAdapter

        if (savedInstanceState != null) {
            selectDate = savedInstanceState.getSerializable("restore_date") as LocalDate
            setTimeSpinnerAdapter()
            selectTime = savedInstanceState.getSerializable("restore_time") as LocalTime
            runningTimes = RunningTimeSetter().getRunningTimes(selectDate)
            dateSpinner.setSelection(runningDates.indexOf(selectDate), false)
            timeSpinner.setSelection(runningTimes.indexOf(selectTime), false)
            count.text = savedInstanceState.getInt("restore_count").toString()
        } else {
            selectDate = movie.startDate
            setTimeSpinnerAdapter()
            runningTimes = RunningTimeSetter().getRunningTimes(selectDate)
            selectTime = runningTimes[0]
        }

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

    private fun initMovieData() {
        title.text = movie.title
        date.text = movie.startDate.toString()
        time.text = movie.runningTime.value.toString()
        description.text = movie.description
    }

    private fun initSetOnClickListener() {
        minus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous--
            if (previous < 0) {
                Toaster.showToast(this, "예약 인원은 음수가 될 수 없습니다")
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
        outState.putSerializable("restore_date", selectDate)
        outState.putSerializable("restore_time", selectTime)
        outState.putInt("restore_count", count.text.toString().toInt())
    }

    fun setTimeSpinnerAdapter() {
        runningTimes = RunningTimeSetter().getRunningTimes(selectDate)
        val timeSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningTimes.map { it.toString() }
        )
        timeSpinner.adapter = timeSpinnerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

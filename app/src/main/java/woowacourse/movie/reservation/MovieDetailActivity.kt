package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.domain.RunningDateSetter
import woowacourse.movie.domain.RunningTimeSetter
import woowacourse.movie.main.MainActivity
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var selectDate: LocalDate
    private lateinit var selectTime: LocalTime

    private lateinit var timeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val image = findViewById<ImageView>(R.id.detail_image)
        val title = findViewById<TextView>(R.id.detail_title)
        val date = findViewById<TextView>(R.id.detail_date)
        val time = findViewById<TextView>(R.id.detail_time)
        val description = findViewById<TextView>(R.id.description)
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)

        val reservationConfirm = findViewById<Button>(R.id.reservation_confirm)

        val minus = findViewById<Button>(R.id.minus)
        val plus = findViewById<Button>(R.id.plus)
        val count = findViewById<TextView>(R.id.count)
        minus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous--
            count.text = previous.toString()
        }
        plus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous++
            count.text = previous.toString()
        }

        val movie = intent.getSerializableExtra(MainActivity.KEY_MOVIE_DATA) as Movie
        Log.d("mendel", "$movie")

        image.setImageDrawable(
            ResourcesCompat.getDrawable(
                image.resources,
                movie.imgResourceId,
                null
            )
        )
        title.text = movie.title
        date.text = movie.startDate.toString()
        time.text = movie.runningTime.toString()
        description.text = movie.description

        reservationConfirm.setOnClickListener {
            val intent = Intent(this, ReservationConfirmActivity::class.java)
            intent.putExtra(MainActivity.KEY_MOVIE_DATA, movie)
            intent.putExtra(KEY_RESERVATION_COUNT, count.text.toString().toInt())
            intent.putExtra(KEY_RESERVATION_DATE, selectDate)
            intent.putExtra(KEY_RESERVATION_TIME, selectTime)
            startActivity(intent)
        }

        selectDate = movie.startDate
        selectTime = RunningTimeSetter().getRunningTimes(selectDate)[0]

        val runningDates = RunningDateSetter().getRunningDates(movie.startDate, movie.endDate)
        val dateSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningDates.map { it.toString() }
        )

        dateSpinner.adapter = dateSpinnerAdapter
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectDate = runningDates[position]
                Log.d("mendel", "$selectDate")
                setTimeSpinnerAdapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun setTimeSpinnerAdapter() {
        timeSpinner = findViewById(R.id.time_spinner)

        val runningTimes = RunningTimeSetter().getRunningTimes(selectDate)
        val timeSpinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            runningTimes.map { it.toString() }
        )

        timeSpinner.adapter = timeSpinnerAdapter
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectTime = runningTimes[position]
                Log.d("hyunji", "$selectTime")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
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

    companion object {
        const val KEY_RESERVATION_COUNT = "count"
        const val KEY_RESERVATION_DATE = "date"
        const val KEY_RESERVATION_TIME = "time"
    }
}

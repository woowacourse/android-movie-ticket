package woowacourse.movie

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val title = findViewById<TextView>(R.id.title)
        val startDate = findViewById<TextView>(R.id.start_date)
        val endDate = findViewById<TextView>(R.id.end_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val poster = findViewById<ImageView>(R.id.movie_poster)
        val movieDate = findViewById<Spinner>(R.id.movie_date)
        val movieTime = findViewById<Spinner>(R.id.movie_time)
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)
        val ticketCount = findViewById<TextView>(R.id.ticket_count)

        poster.setImageResource(intent.getIntExtra("POSTER",0))
        title.text = intent.getStringExtra("TITLE")
        startDate.text = intent.getStringExtra("START_DATE")
        endDate.text = intent.getStringExtra("END_DATE")
        runningTime.text = intent.getStringExtra("RUNNING_TIME")

        val dates = getDates(startDate.text.toString(), endDate.text.toString())
        val movieDatesAdapter = ArrayAdapter(this, R.layout.spinner_item, dates)
        movieDatesAdapter.setDropDownViewResource(R.layout.spinner_item)
        movieDate.adapter = movieDatesAdapter

        val times = getTimes(startDate.text.toString())
        val movieTimesAdapter = ArrayAdapter(this, R.layout.spinner_item, times)
        movieTimesAdapter.setDropDownViewResource(R.layout.spinner_item)
        movieTime.adapter = movieTimesAdapter

        movieDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTimes = getTimes(dates[position])
                val selectedTimesAdapter = ArrayAdapter(this@BookingActivity, R.layout.spinner_item, selectedTimes)
                selectedTimesAdapter.setDropDownViewResource(R.layout.spinner_item)
                movieTime.adapter = selectedTimesAdapter
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        minusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() - 1).toString()
            if(ticketCount.text.toString().toInt()<0) ticketCount.text = "0"
        }

        plusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() + 1).toString()
        }



    }

    private fun getDates(startDate: String, endDate: String): List<String> {
        val parsedStartDate = LocalDate.parse(startDate, dateFormatter)
        val parsedEndDate = LocalDate.parse(endDate, dateFormatter)

        val dates = mutableListOf<String>()
        var current = parsedStartDate

        while (!current.isAfter(parsedEndDate)) {
            dates.add(current.format(dateFormatter))
            current = current.plusDays(1)
        }

        return dates
    }

    private fun getTimes(date: String): List<String> {
        val parsedDate = LocalDate.parse(date, dateFormatter)
        val times = mutableListOf<String>()

        if (parsedDate.dayOfWeek != DayOfWeek.SATURDAY && parsedDate.dayOfWeek != DayOfWeek.SUNDAY) {
            for (hour in 10..24 step 2) {
                val timeStr = String.format("%02d:00", hour)
                times.add(timeStr)
            }
        } else {
            for (hour in 9..24 step 2) {
                val timeStr = String.format("%02d:00", hour)
                times.add(timeStr)
            }
        }

        return times
    }
}

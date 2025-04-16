package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Booking
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        date = LocalDate.now().toString()
        Log.d("오늘 날짜", date)

        val movieData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movieData", Movie::class.java)
            } else {
                intent.getParcelableExtra("movieData")
            } ?: return

        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        bookingTitle.text = movieData.title
        val screeningStartDate = formatDate(movieData.screeningStartDate)
        val screeningEndDate = formatDate(movieData.screeningEndDate)
        bookingScreenDate.text =
            getString(R.string.screening_date_period, screeningStartDate, screeningEndDate)
        bookingRunningTime.text = getString(R.string.minute_text, movieData.runningTime)

        val booking = Booking(movieData)

        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                booking.screeningPeriods(),
            )

        screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    date = screeningDateSpinner.getItemAtPosition(position).toString()
                    extracted(booking)
                    Log.d("spinner_date", date)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        date = screeningDateSpinner.getItemAtPosition(0).toString()

        extracted(booking)
    }

    private fun extracted(booking: Booking) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        screeningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                booking.screeningTimes(date),
            )

        screeningTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    screeningTimeSpinner.getItemAtPosition(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }
}

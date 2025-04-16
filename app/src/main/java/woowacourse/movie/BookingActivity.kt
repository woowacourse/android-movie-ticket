package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movieData", Movie::class.java)
            } else {
                intent.getParcelableExtra<Movie>("movieData") as? Movie
            }

        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        movieData?.let {
            bookingTitle.text = movieData.title
            bookingScreenDate.text = formatDate(movieData.screeningDate)
            bookingRunningTime.text = getString(R.string.minute_text, movieData.runningTime)
        }

        val screeningDateList = listOf("2025.4.1", "2025.4.2")
        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningDateList,
            )

        screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    screeningDateSpinner.getItemAtPosition(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        val screeningTimeList = listOf("11:00", "12:00")
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        screeningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningTimeList,
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

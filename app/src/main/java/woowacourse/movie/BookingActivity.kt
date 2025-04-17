package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.MovieInfo
import woowacourse.movie.data.Ticket
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    private lateinit var movieTime: Spinner
    private lateinit var movieDate: Spinner
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var ticketCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        startDate = findViewById(R.id.start_date)
        endDate = findViewById(R.id.end_date)
        movieDate = findViewById(R.id.movie_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)
        setupPage()

        setupDateChangeListener()
        countButtonHandler()
        confirmButtonHandler()
        if (savedInstanceState != null) {
            repairInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("TICKET_COUNT", ticketCount.text.toString())
        outState.putInt("MOVIE_DATE_POSITION", movieDate.selectedItemPosition)
        outState.putInt("MOVIE_TIME_POSITION", movieTime.selectedItemPosition)
    }

    private fun repairInstanceState(state: Bundle) {
        movieDate.setSelection(state.getInt("MOVIE_DATE_POSITION"))
        movieDate.post {
            movieTime.post {
                movieTime.setSelection(state.getInt("MOVIE_TIME_POSITION"))
            }
        }
        ticketCount.text = state.getString("TICKET_COUNT")
    }

    private fun setupPage() {
        val title = findViewById<TextView>(R.id.title)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val poster = findViewById<ImageView>(R.id.movie_poster)

        val movieInfo = intent.getParcelableExtra<MovieInfo>("MOVIE_INFO")

        if (movieInfo != null) {
            poster.setImageResource(movieInfo.poster)
            title.text = movieInfo.title
            startDate.text = movieInfo.startDate
            endDate.text = movieInfo.endDate
            runningTime.text = movieInfo.runningTime
        }

        SpinnerAdapter.bind(this, movieDate, getDates())
        SpinnerAdapter.bind(this, movieTime, getTimes(startDate.text.toString()))
    }

    private fun setupDateChangeListener() {
        movieDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = getDates()[position]
                    val selectedTimes = getTimes(selectedDate)
                    val adapter =
                        ArrayAdapter(this@BookingActivity, R.layout.spinner_item, selectedTimes)
                    adapter.setDropDownViewResource(R.layout.spinner_item)
                    movieTime.adapter = adapter
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun confirmButtonHandler() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)

        confirmButton.setOnClickListener {
            if (ticketCount.text.toString() == "0") return@setOnClickListener
            askToConfirmBooking()
        }
    }

    private fun countButtonHandler() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() - 1).toString()
            if (ticketCount.text.toString().toInt() < 0) ticketCount.text = "0"
        }

        plusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() + 1).toString()
        }
    }

    private fun getDates(): List<String> {
        val parsedStartDate = LocalDate.parse(startDate.text.toString(), dateFormatter)
        val parsedEndDate = LocalDate.parse(endDate.text.toString(), dateFormatter)

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

        val startHour =
            when (parsedDate.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> 9
                else -> 10
            }

        return (startHour..24 step 2).map { hour ->
            String.format("%02d:00", hour)
        }
    }

    private fun askToConfirmBooking() {
        val ticket = Ticket(
            title = findViewById<TextView>(R.id.title).text.toString(),
            date = movieDate.selectedItem.toString(),
            time = movieTime.selectedItem.toString(),
            count = ticketCount.text.toString(),
            money = (ticketCount.text.toString().toInt() * 13000).toString()
        )

        ConfirmDialog.show(this, ticket) {
            val intent = Intent(this, BookingResultActivity::class.java).apply {
                putExtra("TICKET", ticket)
            }
            startActivity(intent)
        }
    }
}

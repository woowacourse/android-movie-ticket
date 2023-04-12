package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity() {
    private val movieInfo by lazy { intent.getSerializableExtra("movieInfo") as MovieInfo }

    var ticketCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        updateMovieView()
        registerListener()
        registerSpinnerListener()
    }

    private fun registerSpinnerListener() {
        val dateSpinner = findViewById<Spinner>(R.id.reservation_screening_date_spinner)
        val dateList = movieInfo.getScreeningDate()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
        dateSpinner.adapter = adapter

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateTimeView(LocalDate.parse(dateList[position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun updateTimeView(data: LocalDate) {
        val timeSpinner = findViewById<Spinner>(R.id.reservation_screening_time_spinner)
        val timeList = movieInfo.getScreeningTime(data)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        timeSpinner.adapter = adapter
    }

    private fun updateMovieView() {
        val moviePoster = findViewById<ImageView>(R.id.reservation_movie_poster)
        val movieTitle = findViewById<TextView>(R.id.reservation_movie_title)
        val movieReleaseData = findViewById<TextView>(R.id.reservation_movie_release_date)
        val movieRunningTime = findViewById<TextView>(R.id.reservation_movie_running_time)
        val movieSummary = findViewById<TextView>(R.id.reservation_movie_summary)
        with(movieInfo) {
            moviePoster.setImageResource(poster)
            movieTitle.text = title
            movieReleaseData.text = getString(R.string.movie_release_date).format(
                startDate.format(dateTimeFormatter),
                endDate.format(dateTimeFormatter),
            )
            movieRunningTime.text = getString(R.string.movie_running_time).format(runningTime)
            movieSummary.text = summary
        }
    }

    private fun registerListener() {
        registerCountButton()
        registerReservationButton()
    }

    private fun registerCountButton() {
        val decreaseButton = findViewById<TextView>(R.id.reservation_decrease_ticket_button)
        val increaseButton = findViewById<TextView>(R.id.reservation_increase_ticket_button)
        val ticketCountView = findViewById<TextView>(R.id.reservation_ticket_count)
        ticketCountView.text = ticketCount.toString()

        decreaseButton.setOnClickListener {
            ticketCountView.text = (--ticketCount).toString()
        }
        increaseButton.setOnClickListener {
            ticketCountView.text = (++ticketCount).toString()
        }
    }

    private fun registerReservationButton() {
        val reservationButton = findViewById<TextView>(R.id.reservation_complete_button)
        reservationButton.setOnClickListener {
            val intent = Intent(this, MovieTicketActivity::class.java)
            val selectedDate = LocalDate.parse(findViewById<Spinner>(R.id.reservation_screening_date_spinner).selectedItem.toString())
            val selectedTime = LocalTime.parse(findViewById<Spinner>(R.id.reservation_screening_time_spinner).selectedItem.toString())
            val discountPolicy = movieInfo.getDiscountPolicy(selectedDate, selectedTime)
            val movieTicket = MovieTicket(
                discountPolicy(MOVIE_TICKET_PRICE),
                ticketCount,
                selectedDate,
                selectedTime,
            )
            intent.putExtra("movieInfo", movieInfo)
            intent.putExtra("movieTicket", movieTicket)
            ContextCompat.startActivity(this, intent, null)
        }
    }

    companion object {
        private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val MOVIE_TICKET_PRICE = 13000
    }
}

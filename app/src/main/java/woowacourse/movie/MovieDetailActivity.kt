package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.Time
import woowacourse.movie.domain.TimesGenerator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private var peopleCount = PeopleCount()
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var timeSpinnerAdapter: ArrayAdapter<Time>
    private val times = mutableListOf<Time>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = getMovieFromIntent()

        setMovieInfo(movie)
        setDateSpinner(movie)
        setTimeSpinner()
        setPeopleCountController()
        setBookingButton(movie)
    }

    private fun getMovieFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("movie", Movie::class.java)
    } else {
        intent.getSerializableExtra("movie")
    } as Movie

    private fun setMovieInfo(movie: Movie) {
        val moviePoster = findViewById<ImageView>(R.id.detail_poster)
        val movieTitle = findViewById<TextView>(R.id.detail_title)
        val movieDate = findViewById<TextView>(R.id.detail_date)
        val movieTime = findViewById<TextView>(R.id.detail_running_time)
        val movieDescription = findViewById<TextView>(R.id.detail_description)

        moviePoster.setImageResource(movie.poster)
        movieTitle.text = movie.title
        movieDate.text = movie.getScreenDate()
        movieTime.text = movie.getRunningTime()
        movieDescription.text = movie.description
    }

    private fun Movie.getScreenDate(): String = "상영일: ${startDate.format()} ~ ${endDate.format()}"

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    private fun Movie.getRunningTime(): String = "러닝타임: ${time}분"

    private fun setDateSpinner(movie: Movie) {
        dateSpinner = findViewById<Spinner>(R.id.detail_date_spinner)
        val dateSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            movie.getDatesBetweenTwoDates()
        )
        dateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateSpinnerAdapter
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                timeSpinner.setSelection(0)
                times.clear()
                times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
                timeSpinnerAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setTimeSpinner() {
        timeSpinner = findViewById<Spinner>(R.id.detail_time_spinner)
        times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
        timeSpinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )
        timeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeSpinnerAdapter
    }

    private fun setPeopleCountController() {
        val peopleCountView = findViewById<TextView>(R.id.detail_people_count)
        setPeopleCountView(peopleCountView)
        setMinusButton(peopleCountView)
        setPlusButton(peopleCountView)
    }

    private fun setPeopleCountView(peopleCountView: TextView) {
        peopleCountView.text = "${peopleCount.count}"
    }

    private fun setMinusButton(peopleCountView: TextView) {
        val minusButton = findViewById<Button>(R.id.detail_minus_button)
        minusButton.setOnClickListener {
            peopleCount = peopleCount.minusCount()
            setPeopleCountView(peopleCountView)
        }
    }

    private fun setPlusButton(peopleCountView: TextView) {
        val plusButton = findViewById<Button>(R.id.detail_plus_button)
        plusButton.setOnClickListener {
            peopleCount = peopleCount.plusCount()
            setPeopleCountView(peopleCountView)
        }
    }

    private fun setBookingButton(movie: Movie) {
        val bookingButton = findViewById<Button>(R.id.detail_booking_button)

        bookingButton.setOnClickListener {
            moveToTicketActivity(movie)
        }
    }

    private fun moveToTicketActivity(movie: Movie) {
        val ticket = MovieTicket(
            movie.title,
            MovieTime(
                dateSpinner.selectedItem as LocalDate,
                timeSpinner.selectedItem as Time
            ),
            peopleCount
        )

        val intent = Intent(this, MovieTicketActivity::class.java)
        intent.putExtra("ticket", ticket)
        startActivity(intent)
    }
}

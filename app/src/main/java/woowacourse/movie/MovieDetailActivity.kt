package woowacourse.movie

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
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.domain.DayOfWeek
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationDate
import woowacourse.movie.domain.ReservationTime
import woowacourse.movie.domain.RunningDate
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private var numberOfBooker = 1
    private var dateSpinnerPosition = 0
    private var timeSpinnerPosition = 0
    private val selectDateSpinner by lazy { findViewById<Spinner>(R.id.select_date) }
    private val selectTimeSpinner by lazy { findViewById<Spinner>(R.id.select_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        if (savedInstanceState != null) {
            numberOfBooker = savedInstanceState.getInt("BOOKER_NUMBER")
            dateSpinnerPosition = savedInstanceState.getInt("DATE_SPINNER_POSITION")
            timeSpinnerPosition = savedInstanceState.getInt("TIME_SPINNER_POSITION")
        }

        val movie = intent.getSerializableExtra("movie") as Movie

        setDateSpinner(movie.runningDate)

        val moviePoster = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val screeningStartDate = findViewById<TextView>(R.id.screening_start_date)
        val screeningEndDate = findViewById<TextView>(R.id.screening_end_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val description = findViewById<TextView>(R.id.movie_description)
        val minusBtn = findViewById<Button>(R.id.minus_people)
        val booker = findViewById<TextView>(R.id.number_of_people)
        val plusBtn = findViewById<Button>(R.id.plus_people)
        val bookBtn = findViewById<Button>(R.id.book_button)
        val toolbar = findViewById<Toolbar>(R.id.movie_detail_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title
        booker.text = numberOfBooker.toString()

        screeningStartDate.text =
            movie.runningDate.startDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))

        screeningEndDate.text =
            movie.runningDate.endDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))

        runningTime.text = movie.runningTime.toString()
        description.text = movie.description

        minusBtn.setOnClickListener {
            numberOfBooker -= 1
            if (numberOfBooker <= 1) {
                numberOfBooker = 1
            }
            booker.text = numberOfBooker.toString()
        }

        plusBtn.setOnClickListener {
            numberOfBooker += 1
            if (numberOfBooker >= 10) {
                numberOfBooker = 10
            }
            booker.text = numberOfBooker.toString()
        }

        bookBtn.setOnClickListener {
            val selectedDate = LocalDate.parse(selectDateSpinner.selectedItem.toString())
            val selectedTime = LocalTime.parse(selectTimeSpinner.selectedItem.toString())
            val ticket =
                Ticket(
                    13000,
                    LocalDateTime.of(selectedDate, selectedTime),
                    numberOfBooker,
                )
            val intent = Intent(this, TicketActivity::class.java)
            intent.putExtra("ticket", ticket)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                Log.d("ToolBar_item: ", "뒤로가기 버튼 클릭")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun setDateSpinner(date: RunningDate) {
        val dateAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            ReservationDate(date).getScreeningDays(),
        )

        dateAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        selectDateSpinner.adapter = dateAdapter
        selectDateSpinner.setSelection(dateSpinnerPosition)

        selectDateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                setTimeSpinner(LocalDate.parse(selectDateSpinner.getItemAtPosition(position) as String))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun setTimeSpinner(selectedDay: LocalDate) {
        val timeAdapter = ArrayAdapter(
            this@MovieDetailActivity,
            android.R.layout.simple_spinner_item,
            ReservationTime(DayOfWeek.checkDayOfWeek(selectedDay)).getScreeningTimes(),
        )

        timeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        selectTimeSpinner.adapter = timeAdapter
        selectTimeSpinner.setSelection(timeSpinnerPosition)

        selectTimeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                timeSpinnerPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}

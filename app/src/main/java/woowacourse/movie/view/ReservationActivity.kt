package woowacourse.movie.view

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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.R.layout
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationActivity : AppCompatActivity() {
    private var ticketCount: TicketCount = TicketCount()
    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0
    private val formatter: Formatter by lazy { Formatter() }
    private val movie by lazy { getSelectedMovieData() }
    private val movieTime by lazy { MovieTime() }
    private val movieDate by lazy { MovieDate(movie.startDate, movie.endDate) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticketCountTextView = findViewById<TextView>(R.id.tv_reservation_ticket_count)
        setupMovieReservationInfo()
        setupDateAdapter()
        setupTimeAdapter()

        setupSavedData(savedInstanceState)
        setupMinusButtonClick(ticketCountTextView)
        setupPlusButtonClick(ticketCountTextView)
        setupCompleteButtonClick()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getSelectedMovieData(): Movie =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MOVIE_DATA_KEY, Movie::class.java) ?: Movie.value
        } else {
            intent.getSerializableExtra(MOVIE_DATA_KEY) as Movie
        }

    private fun setupMovieReservationInfo() {
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        val poster =
            AppCompatResources.getDrawable(
                this,
                movie.poster,
            )
        posterImageView.setImageDrawable(poster)

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_title)
        movieTitleTextView.text = movie.title

        val screeningDateTextView = findViewById<TextView>(R.id.tv_reservation_screening_date)
        val startDate = formatter.localDateToUI(movie.startDate)
        val endDate = formatter.localDateToUI(movie.endDate)
        screeningDateTextView.text =
            resources.getString(R.string.movie_screening_date, startDate, endDate)

        val runningTimeTextView = findViewById<TextView>(R.id.tv_reservation_running_time)
        val runningTime = movie.runningTime
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)
    }

    private fun setupDateAdapter() {
        val duration: List<LocalDate> = movieDate.getDateTable(LocalDate.now())

        val dateAdapter =
            ArrayAdapter(
                this,
                layout.support_simple_spinner_dropdown_item,
                duration,
            )

        findViewById<Spinner>(R.id.spinner_reservation_date).apply {
            adapter = dateAdapter
            setSelection(selectedDatePosition)
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        movieDate.updateDate(duration[position])
                        selectedDatePosition = position
                        selectedTimePosition = 0
                        setupTimeAdapter()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupTimeAdapter() {
        val timeTable: List<Int> =
            movieTime.getTimeTable(LocalDateTime.now(), movieDate.selectedDate)
        val timeAdapter =
            ArrayAdapter(
                this,
                layout.support_simple_spinner_dropdown_item,
                timeTable.map { formatter.movieTimeToUI(it) },
            )

        findViewById<Spinner>(R.id.spinner_reservation_time).apply {
            adapter = timeAdapter
            setSelection(selectedTimePosition)
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        selectedTimePosition = position
                        movieTime.updateTime(timeTable[position])
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupSavedData(savedInstanceState: Bundle?) {
        val savedCount = savedInstanceState?.getInt(TICKET_COUNT_DATA_KEY) ?: 1
        ticketCount = TicketCount(savedCount)
        findViewById<TextView>(R.id.tv_reservation_ticket_count).text = ticketCount.value.toString()

        selectedDatePosition = savedInstanceState?.getInt(TICKET_DATE_POSITION_DATA_KEY) ?: 0
        selectedTimePosition = savedInstanceState?.getInt(TICKET_TIME_POSITION_DATA_KEY) ?: 0
    }

    private fun setupPlusButtonClick(peopleCountTextView: TextView) {
        findViewById<Button>(R.id.btn_reservation_plus_ticket_count).setOnClickListener {
            ticketCount += 1
            peopleCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun setupMinusButtonClick(peopleCountTextView: TextView) {
        findViewById<Button>(R.id.btn_reservation_minus_ticket_count).setOnClickListener {
            runCatching {
                ticketCount - 1
            }.onSuccess {
                ticketCount -= 1
            }.onFailure { error ->
                Toast
                    .makeText(
                        this,
                        error.message,
                        Toast.LENGTH_SHORT,
                    ).show()
            }
            peopleCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun setupCompleteButtonClick() {
        findViewById<Button>(R.id.btn_reservation_select_complete).setOnClickListener {
            showReservationDialog()
        }
    }

    private fun showReservationDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.reservation_dialog_title))
            .setMessage(getString(R.string.reservation_dialog_message))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.reservation_dialog_cancel)) { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton(getString(R.string.reservation_dialog_complete)) { dialog, _ ->
                val intent = movieTicketIntent()
                startActivity(intent)
                dialog.dismiss()
            }.show()
    }

    private fun movieTicketIntent(): Intent {
        val intent =
            Intent(this, ReservationCompleteActivity::class.java).apply {
                putExtra(
                    ReservationCompleteActivity.Companion.TICKET_DATA_KEY,
                    MovieTicket(
                        title = movie.title,
                        timeStamp =
                            getString(
                                R.string.reservation_ticket_timestamp,
                                formatter.localDateToUI(movieDate.selectedDate),
                                formatter.movieTimeToUI(movieTime.selectedTime),
                            ),
                        count = ticketCount.value,
                    ),
                )
            }
        return intent
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET_COUNT_DATA_KEY, ticketCount.value)
        outState.putInt(TICKET_DATE_POSITION_DATA_KEY, selectedDatePosition)
        outState.putInt(TICKET_TIME_POSITION_DATA_KEY, selectedTimePosition)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val MOVIE_DATA_KEY = "data"
        private const val TICKET_COUNT_DATA_KEY = "count"
        private const val TICKET_DATE_POSITION_DATA_KEY = "date"
        private const val TICKET_TIME_POSITION_DATA_KEY = "time"
    }
}

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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.R.layout
import woowacourse.movie.ReservationCompleteActivity.Companion.TICKET_DATA_KEY
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationActivity : AppCompatActivity() {
    private var selectedDate: LocalDate = LocalDate.now()
    private var ticketCount: Int = 1
    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0
    private val formatter: Formatter by lazy { Formatter() }
    private val movie by lazy { getSelectedMovieData() }
    private val movieTime by lazy { MovieTime() }

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
        setupMovieReservationInfo(movie)
        setupDateAdapter(movie)
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

    private fun setupMovieReservationInfo(movie: Movie) {
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

    private fun setupDateAdapter(movie: Movie) {
        val duration = (movie.startDate..movie.endDate).toList()

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
                        selectedDate = duration[position].toLocalDate()
                        selectedDatePosition = position
                        setupTimeAdapter()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupTimeAdapter() {
        val timeTable: List<Int> = movieTime.getTimeTable(LocalDateTime.now(), selectedDate)
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
        ticketCount = savedInstanceState?.getInt(TICKET_COUNT_DATA_KEY) ?: 1
        findViewById<TextView>(R.id.tv_reservation_ticket_count).text = ticketCount.toString()

        selectedDatePosition = savedInstanceState?.getInt(TICKET_DATE_POSITION_DATA_KEY) ?: 0
        selectedTimePosition = savedInstanceState?.getInt(TICKET_TIME_POSITION_DATA_KEY) ?: 0
    }

    private fun setupPlusButtonClick(peopleCountTextView: TextView) {
        findViewById<Button>(R.id.btn_reservation_plus_ticket_count).setOnClickListener {
            ticketCount += 1
            peopleCountTextView.text = ticketCount.toString()
        }
    }

    private fun setupMinusButtonClick(peopleCountTextView: TextView) {
        findViewById<Button>(R.id.btn_reservation_minus_ticket_count).setOnClickListener {
            if (ticketCount == 1) {
                Toast.makeText(this, "최소 1명은 선택해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            ticketCount -= 1
            peopleCountTextView.text = ticketCount.toString()
        }
    }

    private fun setupCompleteButtonClick() {
        findViewById<Button>(R.id.btn_reservation_select_complete).setOnClickListener {
            AlertDialog
                .Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setCancelable(false)
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton("예매 완료") { dialog, _ ->
                    val intent =
                        Intent(this, ReservationCompleteActivity::class.java).apply {
                            putExtra(
                                TICKET_DATA_KEY,
                                MovieTicket(
                                    movie.title,
                                    "${formatter.localDateToUI(selectedDate)} ${
                                        formatter.movieTimeToUI(
                                            movieTime.selectedTime,
                                        )
                                    }",
                                    ticketCount,
                                ),
                            )
                        }
                    startActivity(intent)
                    dialog.dismiss()
                }.show()
        }
    }

    private fun ClosedRange<LocalDate>.toList(): List<String> {
        val dates = mutableListOf<String>()
        var current = LocalDate.now()
        while (current <= endInclusive) {
            dates.add(formatter.localDateToUI(current))
            current = current.plusDays(1)
        }
        return dates
    }

    private fun String.toLocalDate(): LocalDate {
        val year = slice(0..3).toInt()
        val month = slice(5..6).toInt()
        val date = slice(8..9).toInt()
        return LocalDate.of(year, month, date)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET_COUNT_DATA_KEY, ticketCount)
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

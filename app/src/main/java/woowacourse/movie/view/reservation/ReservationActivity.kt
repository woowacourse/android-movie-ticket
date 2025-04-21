package woowacourse.movie.view.reservation

import android.content.Intent
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
import woowacourse.movie.view.Extras
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.getParcelableExtraCompat
import woowacourse.movie.view.movie.MoviesActivity
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationActivity : AppCompatActivity() {
    private var ticketCount: TicketCount = TicketCount()
    private var selectedDatePosition: Int = 0
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }
    private val movie by lazy { getSelectedMovieData() }
    private val movieTime by lazy { MovieTime() }
    private val movieDate by lazy { MovieDate(movie.startDate, movie.endDate) }
    private val reservationDialog by lazy { ReservationDialog() }

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

        setupMinusButtonClick(ticketCountTextView)
        setupPlusButtonClick(ticketCountTextView)
        setupCompleteButtonClick()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getSelectedMovieData(): Movie =
        try {
            val movie: Movie? = intent.getParcelableExtraCompat(Extras.MovieData.MOVIE_KEY)
            movie
                ?: throw IllegalStateException(R.string.reservation_error_dialog_message.toString())
        } catch (e: Exception) {
            showErrorDialog(e.message.toString())
            Movie.value
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
        val startDate = reservationUiFormatter.localDateToUI(movie.startDate)
        val endDate = reservationUiFormatter.localDateToUI(movie.endDate)
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
                        setupTimeAdapter()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupTimeAdapter() {
        val timeTable: List<Int> =
            movieTime.getTimeTable(LocalDateTime.now(), movieDate.value)
        val timeAdapter =
            ArrayAdapter(
                this,
                layout.support_simple_spinner_dropdown_item,
                timeTable.map { reservationUiFormatter.movieTimeToUI(it) },
            )

        findViewById<Spinner>(R.id.spinner_reservation_time).apply {
            adapter = timeAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        movieTime.updateTime(timeTable[position])
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
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
            showReservationDialog(
                getString(R.string.reservation_dialog_title),
                getString(R.string.reservation_dialog_message),
            )
        }
    }

    private fun showErrorDialog(message: String) {
        reservationDialog.show(
            this,
            getString(R.string.reservation_error_dialog_title),
            message,
            null,
        ) { _ ->
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showReservationDialog(
        title: String,
        message: String,
    ) {
        reservationDialog.show(
            this,
            title,
            message,
            { dialog -> dialog.dismiss() },
            { _ ->
                val intent = movieTicketIntent()
                startActivity(intent)
                finish()
            },
        )
    }

    private fun movieTicketIntent(): Intent {
        val intent =
            Intent(this, ReservationCompleteActivity::class.java).apply {
                putExtra(
                    Extras.TicketData.TICKET_KEY,
                    MovieTicket(
                        title = movie.title,
                        timeStamp =
                            getString(
                                R.string.reservation_ticket_timestamp,
                                reservationUiFormatter.localDateToUI(movieDate.value),
                                reservationUiFormatter.movieTimeToUI(movieTime.value),
                            ),
                        count = ticketCount.value,
                    ),
                )
            }
        return intent
    }

    private fun setupSavedData(savedInstanceState: Bundle?) {
        val savedCount = savedInstanceState?.getInt(Extras.ReservationData.TICKET_COUNT_KEY) ?: 1
        ticketCount = TicketCount(savedCount)
        findViewById<TextView>(R.id.tv_reservation_ticket_count).text = ticketCount.value.toString()

        selectedDatePosition =
            savedInstanceState?.getInt(Extras.ReservationData.DATE_POSITION_KEY) ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Extras.ReservationData.TICKET_COUNT_KEY, ticketCount.value)
        outState.putInt(Extras.ReservationData.DATE_POSITION_KEY, selectedDatePosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupSavedData(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}

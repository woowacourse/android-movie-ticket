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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.TicketCount
import woowacourse.movie.view.Formatter
import woowacourse.movie.view.IntentExtraConstants.MOVIE_DATA_KEY
import woowacourse.movie.view.IntentExtraConstants.TICKET_DATA_KEY
import woowacourse.movie.view.getSerializableExtraData
import woowacourse.movie.view.reservationComplete.ReservationCompleteActivity
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationActivity : AppCompatActivity() {
    private var ticketCount: TicketCount = TicketCount()
    private var selectedDatePosition: Int = 0
    private val formatter: Formatter by lazy { Formatter() }
    private val movie by lazy {
        intent.getSerializableExtraData<Movie>(MOVIE_DATA_KEY) ?: Movie.value
    }
    private val movieTime by lazy { MovieTime() }
    private val movieDate by lazy { MovieDate(movie.startDate, movie.endDate) }

    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_reservation_ticket_count) }
    private val posterImageView: ImageView by lazy { findViewById(R.id.iv_reservation_poster) }
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_reservation_title) }
    private val screeningDateTextView: TextView by lazy { findViewById(R.id.tv_reservation_screening_date) }
    private val runningTimeTextView: TextView by lazy { findViewById(R.id.tv_reservation_running_time) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_time) }
    private val plusButton: Button by lazy { findViewById(R.id.btn_reservation_plus_ticket_count) }
    private val minusButton: Button by lazy { findViewById(R.id.btn_reservation_minus_ticket_count) }
    private val completeButton: Button by lazy { findViewById(R.id.btn_reservation_select_complete) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMovieReservationInfo()
        setupDateAdapter()
        setupTimeAdapter()

        setupSavedData(savedInstanceState)
        setupMinusButtonClick(ticketCountTextView)
        setupPlusButtonClick(ticketCountTextView)
        setupCompleteButtonClick()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupMovieReservationInfo() {
        val poster =
            AppCompatResources.getDrawable(
                this,
                movie.poster,
            )
        posterImageView.setImageDrawable(poster)
        movieTitleTextView.text = movie.title

        val startDate = formatter.localDateToUI(movie.startDate)
        val endDate = formatter.localDateToUI(movie.endDate)
        screeningDateTextView.text =
            resources.getString(R.string.movie_screening_date, startDate, endDate)

        val runningTime = movie.runningTime
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)
    }

    private fun setupDateAdapter() {
        val duration: List<LocalDate> = movieDate.getDateTable(LocalDate.now())

        val dateAdapter =
            ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                duration,
            )
        dateSpinner.apply {
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
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                timeTable.map { formatter.movieTimeToUI(it) },
            )

        timeSpinner.apply {
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

    private fun setupSavedData(savedInstanceState: Bundle?) {
        val savedCount = savedInstanceState?.getInt(TICKET_COUNT_DATA_KEY) ?: 1
        ticketCount = TicketCount(savedCount)
        ticketCountTextView.text = ticketCount.value.toString()

        selectedDatePosition = savedInstanceState?.getInt(TICKET_DATE_POSITION_DATA_KEY) ?: 0
    }

    private fun setupPlusButtonClick(peopleCountTextView: TextView) {
        plusButton.setOnClickListener {
            ticketCount += 1
            peopleCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun setupMinusButtonClick(peopleCountTextView: TextView) {
        minusButton.setOnClickListener {
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
        completeButton.setOnClickListener {
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
                    TICKET_DATA_KEY,
                    MovieTicket(
                        title = movie.title,
                        movieDate = movieDate,
                        movieTime = movieTime,
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
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val TICKET_COUNT_DATA_KEY = "count"
        private const val TICKET_DATE_POSITION_DATA_KEY = "date"
    }
}

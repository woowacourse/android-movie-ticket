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
import woowacourse.movie.model.TicketCount
import woowacourse.movie.view.Extras
import woowacourse.movie.view.movie.MoviesActivity
import java.time.LocalDate

class ReservationActivity :
    AppCompatActivity(),
    ReservationContract.View {
    private lateinit var ticketCountTextView: TextView
    private var selectedDatePosition: Int = 0
    private var ticketCount: TicketCount = TicketCount()
    private val reservationDialog by lazy { ReservationDialog() }
    private val presenter: ReservationPresenter by lazy { ReservationPresenter(this, ticketCount) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ticketCountTextView = findViewById(R.id.tv_reservation_ticket_count)

        presenter.fetchData(this.intent)

        setupMinusButtonClick()
        setupPlusButtonClick()
        setupCompleteButtonClick()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupMovieReservationInfo(
        posterResId: Int,
        title: String,
        startDate: String,
        endDate: String,
        runningTime: Int,
    ) {
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        val poster =
            AppCompatResources.getDrawable(
                this,
                posterResId,
            )
        posterImageView.setImageDrawable(poster)

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_title)
        movieTitleTextView.text = title

        val screeningDateTextView = findViewById<TextView>(R.id.tv_reservation_screening_date)
        screeningDateTextView.text =
            resources.getString(R.string.movie_screening_date, startDate, endDate)

        val runningTimeTextView = findViewById<TextView>(R.id.tv_reservation_running_time)
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)
    }

    override fun updateDateAdapter(
        duration: List<LocalDate>,
        selected: Int,
    ) {
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
                        presenter.onDateSelected(duration[position])
                        selectedDatePosition = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    override fun updateTimeAdapter(times: List<String>) {
        val timeAdapter =
            ArrayAdapter(
                this,
                layout.support_simple_spinner_dropdown_item,
                times,
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
                        presenter.onTimeSelected(position)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupPlusButtonClick() {
        findViewById<Button>(R.id.btn_reservation_plus_ticket_count).setOnClickListener {
            presenter.plusTicketCount()
        }
    }

    private fun setupMinusButtonClick() {
        findViewById<Button>(R.id.btn_reservation_minus_ticket_count).setOnClickListener {
            try {
                presenter.minusTicketCount()
            } catch (e: IllegalArgumentException) {
                Toast
                    .makeText(
                        this,
                        e.message,
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        }
    }

    override fun updateMovieInfo(
        posterResId: Int,
        title: String,
        startDate: String,
        endDate: String,
        runningTime: Int,
    ) {
        setupMovieReservationInfo(posterResId, title, startDate, endDate, runningTime)
    }

    override fun showErrorDialog() {
        reservationDialog.show(
            this,
            getString(R.string.reservation_error_dialog_title),
            getString(R.string.reservation_error_dialog_message),
            null,
        ) { _ ->
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun showReservationDialog(
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

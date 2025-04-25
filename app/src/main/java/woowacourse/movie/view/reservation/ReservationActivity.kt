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
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.reservation.ReservationPresenter
import woowacourse.movie.view.Extras
import woowacourse.movie.view.getParcelableExtraCompat
import woowacourse.movie.view.movie.MoviesActivity
import java.time.LocalDate

class ReservationActivity :
    AppCompatActivity(),
    ReservationContract.View {
    private val reservationDialog by lazy { ReservationDialog() }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_reservation_ticket_count) }
    private val presenter: ReservationPresenter by lazy { ReservationPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.fetchData {
            intent?.getParcelableExtraCompat<Movie>(Extras.MovieData.MOVIE_KEY)
        }
        setupButtonClickListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupButtonClickListener() {
        setupMinusButtonClick()
        setupPlusButtonClick()
        setupCompleteButtonClick()
    }

    override fun updateMovieInfo(
        posterResId: Int,
        title: String,
        startDate: String,
        endDate: String,
        runningTime: Int,
    ) {
        setupMovieReservationInfo(posterResId, title, startDate, endDate, runningTime)
        presenter.initDateAdapter()
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

    override fun setTicketCount(count: Int) {
        ticketCountTextView.text = count.toString()
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
            setSelection(selected)
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        presenter.onDateSelected(duration[position], position)
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

    private fun setupCompleteButtonClick() {
        findViewById<Button>(R.id.btn_reservation_select_complete).setOnClickListener {
            presenter.createTicket { ticket ->
                navigateToSeatSelect(ticket)
            }
        }
    }

    override fun navigateToSeatSelect(ticket: MovieTicket) {
        val intent =
            Intent(this, SeatSelectActivity::class.java).apply {
                putExtra(Extras.TicketData.TICKET_KEY, ticket)
            }
        startActivity(intent)
    }

    private fun setupSavedData(savedInstanceState: Bundle?) {
        val savedCount = savedInstanceState?.getInt(Extras.ReservationData.TICKET_COUNT_KEY) ?: 1
        presenter.restoreTicketCount(savedCount)
        setTicketCount(savedCount)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Extras.ReservationData.TICKET_COUNT_KEY, presenter.currentTicketCount())
        outState.putInt(Extras.ReservationData.DATE_POSITION_KEY, presenter.currentDatePosition())
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

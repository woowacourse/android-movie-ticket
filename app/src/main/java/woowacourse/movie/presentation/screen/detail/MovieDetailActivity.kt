package woowacourse.movie.presentation.screen.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity.Companion.MOVIE_ID
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private val presenter: MovieDetailContract.Presenter by lazy {
        MovieDetailPresenter(this, MovieDao())
    }
    private val addButton: Button by lazy { findViewById(R.id.add_button) }
    private val subButton: Button by lazy { findViewById(R.id.sub_button) }
    private val imageView: ImageView by lazy { findViewById(R.id.reservation_imageview) }
    private val runningTimeTextView: TextView by lazy { findViewById(R.id.reservation_running_time_textview) }
    private val descriptionTextView: TextView by lazy { findViewById(R.id.reservation_description) }
    private val countTextView: TextView by lazy { findViewById(R.id.reservation_count_textview) }
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title_textview) }
    private val screenDateTextView: TextView by lazy { findViewById(R.id.reservation_screen_date_textview) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.screen_date_spinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.screen_time_spinner) }
    private val selectSeatButton: Button by lazy { findViewById(R.id.select_seat_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        presenter.fetchScreenInfo(movieId)
        addClickListenerToCountButtons()
        accClickListenerToSeatButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET, presenter.ticketCount())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(TICKET)
            countTextView.text = count.toString()
            presenter.restoreTicketCount(count)
        }
    }

    override fun setUpView(
        img: Int,
        title: String,
        screenDate: String,
        runningTime: Int,
        description: String,
    ) {
        imageView.setImageResource(img)
        titleTextView.text = title
        screenDateTextView.text = screenDate
        runningTimeTextView.text = runningTime.toString()
        descriptionTextView.text = description
    }

    override fun setUpSpinner(
        dates: List<LocalDate>,
        times: List<LocalTime>,
    ) {
        bindSpinner()
        initDateSpinner(dates)
        updateTimeSpinner(times)
    }

    override fun updateTicketCount(count: Int) {
        countTextView.text = count.toString()
    }

    override fun updateTimeSpinner(times: List<LocalTime>) {
        timeSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
    }

    override fun navigateToReservationSeat(
        movieId: Int,
        ticket: Ticket,
    ) {
        val intent = Intent(this, ReservationSeatActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        intent.putExtra(TICKET, ticket)
        this.startActivity(intent)
    }

    private fun bindSpinner() {
        dateSpinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val item = dateSpinner.adapter.getItem(position) as LocalDate
                    presenter.onSelectedDateTime(item)
                    presenter.registerScreenDate(item)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        timeSpinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.registerScreenTime(timeSpinner.adapter.getItem(position) as LocalTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initDateSpinner(dates: List<LocalDate>) {
        dateSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
    }

    private fun addClickListenerToCountButtons() {
        addButton.setOnClickListener {
            presenter.addTicketCount()
        }
        subButton.setOnClickListener {
            presenter.subTicketCount()
        }
    }

    private fun accClickListenerToSeatButton() {
        selectSeatButton.setOnClickListener {
            presenter.createTicket()
            presenter.loadMovieDetail()
        }
    }

    companion object {
        const val DEFAULT_MOVIE_ID = 0
        const val TICKET = "ticket"
    }
}

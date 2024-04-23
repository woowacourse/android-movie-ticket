package woowacourse.movie.presentation.reservation.booking

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity
import woowacourse.movie.presentation.screen.ScreeningMovieActivity.Companion.MOVIE_ID

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val presenter: ReservationContract.Presenter = ReservationPresenter(this, MovieDao())
    private lateinit var addButton: Button
    private lateinit var subButton: Button
    private lateinit var countTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var screenDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        presenter.fetchMovieDetail(movieId)
        setUpCount()
        bindReservationButton()
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
        initImage(img)
        initTitle(title)
        initScreenDate(screenDate)
        initRunningTime(runningTime)
        descriptionTextView(description)
    }

    override fun updateTicketCount() {
        countTextView.text = presenter.ticketCount().toString()
    }

    private fun initImage(img: Int) {
        val imageView: ImageView = findViewById(R.id.reservation_imageview)
        imageView.setImageResource(img)
    }

    private fun initTitle(title: String) {
        titleTextView = findViewById(R.id.reservation_title_textview)
        titleTextView.text = title
    }

    private fun initScreenDate(localDate: String) {
        screenDateTextView = findViewById(R.id.reservation_screen_date_textview)
        screenDateTextView.text = localDate
    }

    private fun initRunningTime(runningTime: Int) {
        val runningTimeTextView: TextView = findViewById(R.id.reservation_running_time_textview)
        runningTimeTextView.text = runningTime.toString()
    }

    private fun descriptionTextView(description: String) {
        val descriptionTextView: TextView = findViewById(R.id.reservation_description)
        descriptionTextView.text = description
    }

    private fun setUpCount() {
        countTextView = findViewById(R.id.reservation_count_textview)
        countTextView.text = presenter.ticketCount().toString()
        bindCountButtons()
    }

    private fun bindCountButtons() {
        addButton = findViewById(R.id.add_button)
        subButton = findViewById(R.id.sub_button)

        addButton.setOnClickListener {
            presenter.addTicketCount()
        }
        subButton.setOnClickListener {
            presenter.subTicketCount()
        }
    }

    private fun bindReservationButton() {
        val reservationButton: Button = findViewById(R.id.reservation_complete_button)

        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            presenter.clickReservationCompleteButton(
                intent,
            )
            this.startActivity(intent)
        }
    }

    companion object {
        const val DEFAULT_MOVIE_ID = 0
        const val TICKET = "ticket"
    }
}

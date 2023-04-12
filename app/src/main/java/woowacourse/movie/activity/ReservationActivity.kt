package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import domain.Reservation
import domain.TicketCount
import woowacourse.movie.R

class ReservationActivity : AppCompatActivity() {

    private val posterImageView: ImageView by lazy {
        findViewById(R.id.reservation_movie_image_view)
    }
    private val movieNameTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_name_text_view)
    }
    private val screeningDateTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_screening_date_text_view)
    }
    private val runningTimeTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_running_time_text_view)
    }
    private val descriptionTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_description_text_view)
    }
    private val minusButton: Button by lazy {
        findViewById(R.id.reservation_ticket_count_minus_button)
    }
    private val plusButton: Button by lazy {
        findViewById(R.id.reservation_ticket_count_plus_button)
    }
    private val ticketCountTextView: TextView by lazy {
        findViewById(R.id.reservation_ticket_count_text_view)
    }
    private val completeButton: Button by lazy {
        findViewById(R.id.reservation_complete_button)
    }
    private val movie: Movie by lazy {
        intent.getSerializableExtra(getString(R.string.movie_key)) as Movie
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initReservationView()
        initClickListener()
    }

    private fun initReservationView() {
        with(movie) {
            posterImage?.let { id -> posterImageView.setImageResource(id) }
            movieNameTextView.text = name
            screeningDateTextView.text = getString(R.string.screening_date_form).format(
                screeningDate.year,
                screeningDate.monthValue,
                screeningDate.dayOfMonth
            )
            runningTimeTextView.text = getString(R.string.running_time_form).format(runningTime)
            descriptionTextView.text = description
        }
    }

    private fun initClickListener() {
        initMinusClickListener()
        initPlusClickListener()
        initCompleteButton()
    }

    private fun initMinusClickListener() {
        minusButton.setOnClickListener {
            runCatching {
                val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() - 1)
                ticketCountTextView.text = ticketCount.value.toString()
            }.onFailure {
                Toast.makeText(this, TICKET_CONDITION, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initPlusClickListener() {
        plusButton.setOnClickListener {
            val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() + 1)
            ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun initCompleteButton() {
        completeButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            val reservation: Reservation = Reservation.from(movie, ticketCount)
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra(getString(R.string.reservation_key), reservation)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        private const val TICKET_CONDITION = "티켓 수는 ${TicketCount.MINIMUM}장 이상이어야합니다."
    }
}

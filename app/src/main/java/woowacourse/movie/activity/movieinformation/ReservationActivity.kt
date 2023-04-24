package woowacourse.movie.activity.movieinformation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.seats.SeatSelectionActivity
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_INSTANCE_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_TIME_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_TIME_INSTANCE_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INSTANCE_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INTENT_KEY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }

    private val dateTimeSpinnerView by lazy { DateTimeSpinnerView(binding) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel? = intent.getSerializableExtra(MOVIE_INTENT_KEY) as? MovieModel
        if (movieModel == null) {
            showToast(getString(R.string.invalid_access_message))
            finish()
        }

        MovieInformationView(binding, movieModel!!).set()
        TicketCountSelectorView(binding).set(savedInstanceState)
        dateTimeSpinnerView.set(movieModel, savedInstanceState)
        binding.seatSelectionButton.setOnClickListener { seatSelectionButtonClickEvent(movieModel) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(
            TICKET_COUNT_INSTANCE_KEY,
            binding.ticketCountTextView.text.toString().toInt()
        )
        outState.putLong(SCREENING_DATE_INSTANCE_KEY, dateTimeSpinnerView.getSelectedDate().toEpochDay())
        outState.putString(SCREENING_TIME_INSTANCE_KEY, dateTimeSpinnerView.getSelectedTime().toString())
    }

    private fun seatSelectionButtonClickEvent(movieModel: MovieModel) {
        val selectedDate: LocalDate = dateTimeSpinnerView.getSelectedDate()
        val selectedTime: LocalTime = dateTimeSpinnerView.getSelectedTime()

        val intent = Intent(this, SeatSelectionActivity::class.java)
        intent.putExtra(MOVIE_INTENT_KEY, movieModel)
        intent.putExtra(
            TICKET_COUNT_INTENT_KEY,
            binding.ticketCountTextView.text.toString().toInt()
        )
        intent.putExtra(
            SCREENING_DATE_TIME_INTENT_KEY,
            LocalDateTime.of(selectedDate, selectedTime)
        )

        startActivity(intent)
        finish()
    }

    private fun showToast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

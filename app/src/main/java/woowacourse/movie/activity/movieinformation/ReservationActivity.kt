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
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_TIME_INSTANCE_KEY
import woowacourse.movie.uimodel.ReservationOptionModel
import woowacourse.movie.uimodel.ReservationOptionModel.Companion.RESERVATION_OPTION_INTENT_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INSTANCE_KEY

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

        MovieInformationView(binding).set(movieModel!!)
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
        outState.putLong(SCREENING_DATE_INSTANCE_KEY, dateTimeSpinnerView.selectedDate.toEpochDay())
        outState.putString(SCREENING_TIME_INSTANCE_KEY, dateTimeSpinnerView.selectedTime.toString())
    }

    private fun seatSelectionButtonClickEvent(movieModel: MovieModel) {

        val intent = Intent(this, SeatSelectionActivity::class.java)
        val reservationOptionModel = ReservationOptionModel(
            movieModel,
            binding.ticketCountTextView.text.toString().toInt(),
            dateTimeSpinnerView.selectedDateTime
        )
        intent.putExtra(RESERVATION_OPTION_INTENT_KEY, reservationOptionModel)
        startActivity(intent)
        finish()
    }

    private fun showToast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

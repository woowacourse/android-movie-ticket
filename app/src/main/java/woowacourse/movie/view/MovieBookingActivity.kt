package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MovieBooking
import woowacourse.movie.adpater.BookedDateSpinnerAdapter
import woowacourse.movie.adpater.BookedTimeSpinnerAdapter
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieBookingBinding
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.helper.BuildVersion
import woowacourse.movie.helper.LocalDateHelper.toDotFormat
import woowacourse.movie.presenter.MovieBookingPresenter
import woowacourse.movie.view.MovieBookedActivity.Companion.movieBookedIntent
import java.time.LocalDate
import java.time.LocalTime

class MovieBookingActivity : AppCompatActivity(), MovieBooking.View {
    private lateinit var binding: MovieBookingBinding
    private lateinit var presenter: MovieBookingPresenter
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MovieBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        movie = BuildVersion().getParcelableClass(
            intent,
            KEY_MOVIE, Movie::class
        )
        presenter = MovieBookingPresenter(this@MovieBookingActivity)
        presenter.loadMovie(movie)

        setupDatePicker()
        setupTimePicker()
        setupMemberCount()
        setupBookingCompleteButton()
    }

    override fun showMovieInfo() {
        binding.movieTitle.text = movie.title
        binding.moviePoster.setImageResource(movie.poster)
        binding.movieDate.text = getString(
            R.string.movie_screening_date,
            movie.screeningPeriod.screeningStartDate.toDotFormat(),
            movie.screeningPeriod.screeningEndDate.toDotFormat()
        )
        binding.movieRunningTime.text = getString(R.string.movie_running_time, movie.runningTime)
    }

    override fun updateMemberCount(count: Int) {
        binding.memberCount.text = count.toString()
    }

    override fun showBookingDate(dates: List<LocalDate>) {
        binding.datePicker.adapter = BookedDateSpinnerAdapter(dates)
    }

    override fun showBookingTimes(times: List<LocalTime>) {
        binding.timePicker.adapter = BookedTimeSpinnerAdapter(times)
    }

    override fun showConfirmDialog(bookingStatus: BookingStatus) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.check_movie_booking))
            .setMessage(getString(R.string.confirm_reservation_message))
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                navigateToMovieBooked(bookingStatus)
            }
            .show()
            .setCancelable(false)
    }

    override fun navigateToMovieBooked(bookingStatus: BookingStatus) {
        val intent = movieBookedIntent(this, bookingStatus)
        startActivity(intent)
        finish()
    }

    override fun showError(messageResId: Int) {
        AlertDialog.Builder(this)
            .setMessage(getString(messageResId))
            .setPositiveButton(R.string.error_dialog_okay, null)
            .show()
            .setCancelable(false)
    }

    private fun setupDatePicker() {
        binding.datePicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDate = (parent.adapter as BookedDateSpinnerAdapter).getItem(position)
                presenter.selectDate(selectedDate)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupTimePicker() {
        binding.timePicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTime = (parent.adapter as BookedTimeSpinnerAdapter).getItem(position)
                presenter.selectTime(selectedTime)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupMemberCount() {
        binding.plusMemberCount.setOnClickListener { presenter.increaseCount() }
        binding.minusMemberCount.setOnClickListener { presenter.decreaseCount() }
    }

    private fun setupBookingCompleteButton() {
        binding.bookingCompleteButton.setOnClickListener { presenter.confirmBooking() }
    }

    companion object {
        private const val KEY_MOVIE = "movie"

        fun movieBookingIntent(otherActivity: AppCompatActivity, movie: Movie): Intent {
            return Intent(otherActivity, MovieBookingActivity::class.java)
                .apply { putExtra(KEY_MOVIE, movie) }
        }
    }
}

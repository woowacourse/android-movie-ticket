package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MovieBookingSeat
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieBookingSeatBinding
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.helper.BuildVersion
import woowacourse.movie.helper.CustomClickListenerHelper.setOnSingleClickListener
import woowacourse.movie.presenter.MovieBookingSeatPresenter
import woowacourse.movie.view.MovieBookedActivity.Companion.movieBookedIntent

class MovieBookingSeatActivity : AppCompatActivity(), MovieBookingSeat.View {
    private lateinit var binding: MovieBookingSeatBinding
    private lateinit var presenter: MovieBookingSeatPresenter
    private lateinit var bookingStatus: BookingStatus
    private var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MovieBookingSeatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking_seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bookingStatus =
            BuildVersion().getParcelableClass(intent, KEY_BOOKING_SEAT, BookingStatus::class)
        presenter = MovieBookingSeatPresenter(this@MovieBookingSeatActivity)
        presenter.loadBookingStatus(bookingStatus)
    }

    override fun showBookingStatusInfo() {
        binding.screenText.text = getString(R.string.screen)
        binding.movieTitle.text = bookingStatus.movie.title
        binding.moviePrice.text = binding.moviePrice.context.getString(
            R.string.booking_seat_price,
            price
        )
        binding.confirmButton.setOnSingleClickListener { showConfirmDialog(bookingStatus) }
        binding.confirmButton.text = getString(R.string.booking_seat_okay)
    }

    override fun updateSeatCount(count: Int) {
        TODO("Not yet implemented")
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
        val intent =  movieBookedIntent(this@MovieBookingSeatActivity, bookingStatus)
        startActivity(intent)
        finish()
    }

    override fun showError(messageRes: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val KEY_BOOKING_SEAT = "bookingSeat"

        fun movieBookingSeatIntent(
            otherActivity: AppCompatActivity,
            bookingStatus: BookingStatus
        ): Intent {
            return Intent(otherActivity, MovieBookingSeatActivity::class.java)
                .apply { putExtra(KEY_BOOKING_SEAT, bookingStatus) }
        }
    }
}

package woowacourse.movie.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.adapter.ReservationDaySpinnerAdapter
import woowacourse.movie.adapter.RunningTimeSpinnerAdapter
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieDateTime
import woowacourse.movie.domain.RunningTimes
import woowacourse.movie.global.getObjectFromIntent
import woowacourse.movie.global.newIntent
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationBinding
    private lateinit var reservationDay: LocalDate
    private lateinit var runningDateTime: LocalTime
    private var runningTimePosition: Int = 0
    private var datePosition: Int = 0
    private var isSpinnerInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = intent.getObjectFromIntent<Movie>(MainActivity.MOVIE_KEY)
        val memberCount = MEMBER_COUNT_DEFAULT
        binding.count.text = memberCount
        binding.datePickerActions.adapter =
            ReservationDaySpinnerAdapter(
                MovieDateTime(movie.startDateTime, movie.endDateTime).betweenDates(),
            )
        reservationDay = binding.datePickerActions.selectedItem as LocalDate

        binding.datePickerActions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    if (!isSpinnerInitialized) {
                        isSpinnerInitialized = true
                        return
                    }
                    reservationDay = parent?.getItemAtPosition(position) as LocalDate
                    binding.timePickerActions.adapter =
                        RunningTimeSpinnerAdapter(
                            RunningTimes().runningTimes(reservationDay),
                        )
                    datePosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.timePickerActions.adapter =
            RunningTimeSpinnerAdapter(
                RunningTimes().runningTimes(reservationDay),
            )
        runningDateTime = binding.timePickerActions.selectedItem as LocalTime
        binding.timePickerActions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    runningDateTime = parent?.getItemAtPosition(position) as LocalTime
                    runningTimePosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.plusButton.setOnClickListener {
            binding.count.text = binding.count.text.toString()
                .toIntOrNull()
                ?.plus(1)
                ?.toString()
                ?: MEMBER_COUNT_DEFAULT
        }

        binding.minusButton.setOnClickListener {
            if (memberCount.toString().toInt() <= MINIMUM_MEMBER_COUNT) {
                Toast.makeText(this, getString(R.string.request_least_one_person), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.count.text = binding.count.text.toString()
                .toIntOrNull()
                ?.minus(1)
                ?.toString()
                ?: MEMBER_COUNT_DEFAULT
        }

        binding.commonButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.complete_dialog_title))
                .setMessage(getString(R.string.complete_dialog_message))
                .setPositiveButton(getString(R.string.complete_dialog_positive_button)) { _, _ ->
                    navigateToReservationComplete(
                        BookingStatus(
                            movie = movie,
                            isBooked = true,
                            memberCount = MemberCount(binding.count.text.toString().toInt()),
                            bookedTime = LocalDateTime.of(reservationDay, runningDateTime),
                        ),
                    )
                }
                .setNegativeButton(R.string.complete_dialog_negative_button) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
                .setCancelable(false)
        }

        binding.bookedMovieRunningDayText.text =
            binding.bookedMovieRunningDayText.context.getString(
                R.string.movie_screening_date,
                movie.startDateTime,
                movie.endDateTime,
            )
        binding.bookedMovieTitleText.text = movie.title
        binding.bookedMovieRunningTimeText.text =
            binding.bookedMovieRunningTimeText.context.getString(
                R.string.movie_running_time,
                movie.runningTime.inWholeMinutes,
            )
    }

    private fun navigateToReservationComplete(bookingStatus: BookingStatus) {
        val intent = newIntent<ReservationCompleteActivity>(listOf(BOOKING_STATUS_KEY to bookingStatus))
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MEMBER_COUNT_KEY, binding.count.text.toString())
        outState.putInt(RUNNING_TIME_KEY, runningTimePosition)
        outState.putInt(RESERVATION_DAY_KEY, datePosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.count.text = savedInstanceState.getString(MEMBER_COUNT_KEY)
        binding.timePickerActions.setSelection(savedInstanceState.getInt(RUNNING_TIME_KEY))
        binding.datePickerActions.setSelection(savedInstanceState.getInt(RESERVATION_DAY_KEY))
    }

    companion object {
        const val MEMBER_COUNT_KEY = "memberCount"
        const val RUNNING_TIME_KEY = "runningTime"
        const val RESERVATION_DAY_KEY = "reservationDay"
        const val BOOKING_STATUS_KEY = "bookingStatus"
        const val MEMBER_COUNT_DEFAULT = "1"
        const val MINIMUM_MEMBER_COUNT = 1
    }
}

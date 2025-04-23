package woowacourse.movie.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.global.ServiceLocator
import woowacourse.movie.global.getObjectFromIntent
import woowacourse.movie.global.setImage
import woowacourse.movie.global.toFormattedDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : AppCompatActivity() {
    private lateinit var reservationDay: LocalDate
    private lateinit var runningDateTime: LocalTime
    private lateinit var screeningTimeAdapter: RunningTimeSpinnerAdapter
    private var runningTimePosition: Int = DEFAULT_POSITION
    private var datePosition: Int = DEFAULT_POSITION
    private var memberCount = MEMBER_COUNT_DEFAULT
    private val runningTimeRule = ServiceLocator.runningTimeRule
    private val today = ServiceLocator.today
    private val now = ServiceLocator.now
    private val binding: ActivityReservationBinding by lazy {
        ActivityReservationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MEMBER_COUNT_KEY, memberCount)
        outState.putInt(RUNNING_TIME_KEY, runningTimePosition)
        outState.putInt(RESERVATION_DAY_KEY, datePosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        memberCount = savedInstanceState.getInt(MEMBER_COUNT_KEY)
        binding.count.text = memberCount.toString()
        binding.datePickerActions.setSelection(savedInstanceState.getInt(RESERVATION_DAY_KEY))
        binding.timePickerActions.setSelection(savedInstanceState.getInt(RUNNING_TIME_KEY))
    }

    private fun init() {
        val movie = intent.getObjectFromIntent<MovieDto>(MOVIE_KEY).toMovie()
        setBindingText(movie)
        setScreeningDate(movie)
        setScreeningTime()
        setCounterEventListener()
        setCompleteButtonEventListener(movie)
    }

    private fun setBindingText(movie: Movie) {
        binding.count.text = memberCount.toString()
        binding.bookedMovieRunningDayText.text =
            getString(
                R.string.movie_screening_date,
                movie.startDateTime.toFormattedDate(),
                movie.endDateTime.toFormattedDate(),
            )
        binding.bookedMovieTitleText.text = movie.title
        binding.bookedMovieRunningTimeText.text =
            getString(
                R.string.movie_running_time,
                movie.runningTime.inWholeMinutes,
            )
        binding.moviePoster.setImage(movie.posterUrl)
    }

    private fun setCounterEventListener() {
        binding.plusButton.setOnClickListener {
            memberCount++
            binding.count.text = memberCount.toString()
        }

        binding.minusButton.setOnClickListener {
            if (memberCount <= MINIMUM_MEMBER_COUNT) {
                Toast.makeText(this, getString(R.string.request_least_one_person), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            memberCount--
            binding.count.text = memberCount.toString()
        }
    }

    private fun setCompleteButtonEventListener(movie: Movie) {
        binding.commonButton.setOnClickListener {
            dialog {
                onPositiveButtonClicked {
                    navigateToReservationComplete(
                        BookingStatus(
                            movie = movie,
                            isBooked = true,
                            memberCount = MemberCount(memberCount),
                            bookedTime = LocalDateTime.of(reservationDay, runningDateTime),
                        ),
                    )
                }
            }.show()
        }
    }

    private fun setScreeningDate(movie: Movie) {
        binding.datePickerActions.adapter =
            ReservationDaySpinnerAdapter(
                movie.betweenDates(today),
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
                    reservationDay = parent?.getItemAtPosition(position) as LocalDate
                    screeningTimeAdapter.changeItems(runningTimeRule.whenTargetDay(reservationDay, now))
                    runningTimePosition = DEFAULT_POSITION
                    datePosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun setScreeningTime() {
        screeningTimeAdapter =
            RunningTimeSpinnerAdapter(
                runningTimeRule.whenTargetDay(reservationDay, now),
            )
        binding.timePickerActions.adapter = screeningTimeAdapter
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

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun navigateToReservationComplete(bookingStatus: BookingStatus) {
        val intent =
            ReservationCompleteActivity
                .newIntent(this, ReservationDto.fromBookingStatus(bookingStatus))
        startActivity(intent)
    }

    private fun dialog(block: DialogBuilder.() -> Unit): AlertDialog {
        return DialogBuilder(this).apply(block).build()
    }

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val MEMBER_COUNT_KEY = "memberCount"
        private const val RUNNING_TIME_KEY = "runningTime"
        private const val RESERVATION_DAY_KEY = "reservationDay"
        private const val MEMBER_COUNT_DEFAULT = 1
        private const val MINIMUM_MEMBER_COUNT = 1
        private const val DEFAULT_POSITION = 0

        fun newIntent(
            from: Context,
            dto: MovieDto,
        ): Intent {
            return Intent(from, ReservationActivity::class.java)
                .apply { putExtra(MOVIE_KEY, dto) }
        }
    }
}

private class DialogBuilder(val context: Context) {
    private var dialog: AlertDialog.Builder =
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.complete_dialog_title))
            .setMessage(context.getString(R.string.complete_dialog_message))
            .setNegativeButton(R.string.complete_dialog_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)

    fun onPositiveButtonClicked(block: () -> Unit): DialogBuilder {
        dialog.setPositiveButton(context.getString(R.string.complete_dialog_positive_button)) { _, _ ->
            block()
        }
        return this
    }

    fun build(): AlertDialog = dialog.create()
}

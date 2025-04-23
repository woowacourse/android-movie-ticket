package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MovieActivity.Companion.KEY_MOVIE
import woowacourse.movie.LocalDateHelper.toDotFormat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTimes
import java.time.LocalDate
import java.time.LocalTime

class MovieBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.movie_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = movie()
        var count = 1
        var bookedDate: LocalDate = movie.screeningPeriod.betweenDates()[0]
        var bookedTimes: List<LocalTime> = emptyList()
        var bookedTime: LocalTime = LocalTime.now()

        // 영화 정보
        val title: TextView = findViewById(R.id.movie_title)
        val poster: ImageView = findViewById(R.id.movie_poster)
        val screeningDate: TextView = findViewById(R.id.movie_date)
        val runningTimes: TextView = findViewById(R.id.movie_running_time)

        // 날짜, 시간 선택
        val date: Spinner = findViewById(R.id.date_picker)
        val time: Spinner = findViewById(R.id.time_picker)

        // 인원 수 선택
        val plusMemberCount: Button = findViewById(R.id.plus_member_count)
        val minusMemberCount: Button = findViewById(R.id.minus_member_count)
        val memberCount: TextView = findViewById(R.id.member_count)

        // 선택 완료 버튼
        val bookingComplete: Button = findViewById(R.id.booking_complete_button)

        initMovie(title, movie, poster, screeningDate, runningTimes)
        memberCount(memberCount, count, plusMemberCount, minusMemberCount)

        val pair = updateBookedDate(date, movie, bookedDate, bookedTimes, time)
        bookedDate = pair.first
        bookedTimes = pair.second

        bookedTime = updateBookedTime(time, bookedTimes, bookedTime)

        bookMovie(bookingComplete, movie, count, bookedDate, bookedTime)
    }

    private fun bookMovie(
        bookingComplete: Button,
        movie: Movie,
        count: Int,
        bookedDate: LocalDate,
        bookedTime: LocalTime
    ) {
        bookMovieButton(
            bookingComplete,
            movie,
            { count },
            { bookedDate },
            { bookedTime },
        )
    }

    private fun updateBookedTime(
        time: Spinner,
        bookedTimes: List<LocalTime>,
        bookedTime: LocalTime
    ): LocalTime {
        var bookedTime1 = bookedTime
        time.adapter = BookedTimeSpinnerAdapter(bookedTimes)
        time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTime = (parent.adapter as BookedTimeSpinnerAdapter).getItem(position)
                bookedTime1 = selectedTime
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return bookedTime1
    }

    private fun updateBookedDate(
        date: Spinner,
        movie: Movie,
        bookedDate: LocalDate,
        bookedTimes: List<LocalTime>,
        time: Spinner
    ): Pair<LocalDate, List<LocalTime>> {
        var bookedDate1 = bookedDate
        var bookedTimes1 = bookedTimes
        date.adapter = BookedDateSpinnerAdapter(movie.screeningPeriod.betweenDates())
        date.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDate = (parent.adapter as BookedDateSpinnerAdapter).getItem(position)
                bookedDate1 = selectedDate
                bookedTimes1 = RunningTimes(bookedDate1).runningTimes()
                time.adapter = BookedTimeSpinnerAdapter(bookedTimes1)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
        return Pair(bookedDate1, bookedTimes1)
    }

    private fun memberCount(
        memberCount: TextView,
        count: Int,
        plusMemberCount: Button,
        minusMemberCount: Button
    ) {
        var count1 = count
        memberCount.text = count1.toString()

        plusMemberCount.setOnClickListener {
            count1++
            memberCount.text = count1.toString()
        }
        minusMemberCount.setOnClickListener {
            if (count1 <= 1) {
                throw IllegalStateException(getString(R.string.error_people_over_one))
                return@setOnClickListener
            }
            count1--
            memberCount.text = count1.toString()
        }
    }

    private fun bookMovieButton(
        bookingComplete: Button,
        movie: Movie,
        count: () -> Int,
        bookedDate: () -> LocalDate,
        bookedTime: () -> LocalTime,
    ) {
        bookingComplete.setOnClickListener {
            val bookingStatus = BookingStatus(movie, count(), bookedDate(), bookedTime())
            AlertDialog.Builder(this@MovieBookingActivity)
                .setTitle(getString(R.string.check_movie_booking))
                .setMessage(getString(R.string.confirm_reservation_message))
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.okay)) { _, _ ->
                    navigateToReservationComplete(bookingStatus)
                }
                .show()
                .setCancelable(false)
        }
    }

    private fun initMovie(
        title: TextView,
        movie: Movie,
        poster: ImageView,
        screeningDate: TextView,
        runningTimes: TextView
    ) {
        title.text = movie.title
        poster.setImageResource(movie.poster)
        screeningDate.text = screeningDate.context.getString(
            R.string.movie_screening_date,
            movie.screeningPeriod.screeningStartDate.toDotFormat(),
            movie.screeningPeriod.screeningEndDate.toDotFormat(),
        )
        runningTimes.text = runningTimes.context.getString(
            R.string.movie_running_time,
            movie.runningTime,
        )
    }

    private fun navigateToReservationComplete(
        bookingStatus: BookingStatus
    ) {
        val intent = Intent(
            this@MovieBookingActivity,
            MovieBookedActivity::class.java
        ).putExtra(KEY_BOOKING_STATUS, bookingStatus)
        finish()
        startActivity(intent)
    }

    private fun movie(): Movie {
        return BuildVersion().getParcelableClass(intent, KEY_MOVIE, Movie::class)
    }

    companion object {
        const val KEY_BOOKING_STATUS = "bookingStatus"
    }
}

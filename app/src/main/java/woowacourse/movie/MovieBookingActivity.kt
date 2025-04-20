package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MovieTicketActivity.Companion.KEY_MOVIE
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTimes
import java.time.LocalDate
import java.time.LocalDateTime

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
        val bookingStatus =
            BookingStatus(movie, true, MemberCount(2), LocalDateTime.of(2024, 1, 1, 0, 0, 0))
        var count = 1

        // 영화 정보
        val title: TextView = findViewById(R.id.movie_title)
        val poster: ImageView = findViewById(R.id.movie_poster)
        val screeningDate: TextView = findViewById(R.id.movie_screening_date)
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

        adaptMovie(title, movie, poster, screeningDate, runningTimes)
        memberCount(memberCount, count, plusMemberCount, minusMemberCount)
        bookMovieButton(bookingComplete, bookingStatus)

        date.adapter = ReservationDaySpinnerAdapter(movie.screeningPeriod.betweenDates())
        time.adapter = RunningTimeSpinnerAdapter(RunningTimes().runningTimes(LocalDate.now()))
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
                throw IllegalStateException("영화 예매 인원은 1명이상 선택해야 합니다")
                return@setOnClickListener
            }
            count1--
            memberCount.text = count1.toString()
        }
    }

    private fun bookMovieButton(
        bookingComplete: Button,
        bookingStatus: BookingStatus
    ) {
        bookingComplete.setOnClickListener {
            AlertDialog.Builder(this@MovieBookingActivity)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("예매 완료") { _, _ ->
                    navigateToReservationComplete(bookingStatus)
                }
                .show()
                .setCancelable(false)
        }
    }

    private fun adaptMovie(
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
        startActivity(intent)
    }

    private fun movie(): Movie {
        return BuildVersion().getParcelableClass(intent, KEY_MOVIE, Movie::class)
    }

    companion object {
        const val KEY_BOOKING_STATUS = "bookingStatus"
    }
}



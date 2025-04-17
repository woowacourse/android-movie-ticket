package woowacourse.movie

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = movie()
        val memberPlusButton = findViewById<Button>(R.id.plus_button)
        val memberMinusButton = findViewById<Button>(R.id.minus_button)
        val memberCommonButton = findViewById<Button>(R.id.common_button)
        val memberCount = findViewById<TextView>(R.id.count)
        val bookedRunningDayText = findViewById<TextView>(R.id.booked_movie_running_day_text)
        val titleTextView = findViewById<TextView>(R.id.booked_movie_title_text)
        val runningTimeTextView = findViewById<TextView>(R.id.booked_movie_running_time_text)
        val runningTimeSpinner = findViewById<Spinner>(R.id.time_picker_actions)
        val reservationDaySpinner = findViewById<Spinner>(R.id.date_picker_actions)

        val runningTimeArray = resources.getStringArray(R.array.running_time_array)
        val runningTimeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, runningTimeArray)

        val reservationDayArray = resources.getStringArray(R.array.reservation_day_array)
        val reservationDayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, reservationDayArray)

        runningTimeSpinner.adapter = runningTimeAdapter
        reservationDaySpinner.adapter = reservationDayAdapter

        runningTimeSpinner.setSelection(0)
        reservationDaySpinner.setSelection(0)

        val runningDateTime = runningTimeSpinner.selectedItem as String
        val reservationDay = reservationDaySpinner.selectedItem as String


        memberPlusButton.setOnClickListener {
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.plus(1)
                ?.toString()
                ?: "1"
        }


        memberMinusButton.setOnClickListener {
            if (memberCount.text.toString().toInt() <= 1) {
                Toast.makeText(this, "1명 이상의 인원을 선택해야 합니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.minus(1)
                ?.toString()
                ?: "1"
        }

        memberCommonButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    navigateToReservationComplete(
                        BookingStatus(
                            movie = movie(),
                            isBooked = true,
                            memberCount = MemberCount(memberCount.text.toString().toInt()),
                            bookedTime = LocalDateTime.parse("$reservationDay,$runningDateTime",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm"))
                        )
                    )
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
                .setCancelable(false)
        }


        bookedRunningDayText.text = bookedRunningDayText.context.getString(
            R.string.movie_screening_date,
            movie.startDateTime,
            movie.endDateTime
        )
        titleTextView.text = movie.title
        runningTimeTextView.text = runningTimeTextView.context.getString(
            R.string.movie_running_time,
            movie.runningTime.inWholeMinutes
        )
    }

    private fun navigateToReservationComplete(
        bookingStatus: BookingStatus
    ) {
        val intent = Intent(this, ReservationCompleteActivity::class.java)
            .apply { putExtra("bookingStatus", bookingStatus) }
        startActivity(intent)
    }

    private fun movie(): Movie {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("movie", Movie::class.java) ?: throw IllegalStateException()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movie") as? Movie ?: throw IllegalStateException()
        }
    }
}
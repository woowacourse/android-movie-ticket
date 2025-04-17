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
import woowacourse.movie.databinding.BookingBinding
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {
    private lateinit var binding: BookingBinding

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
        binding = BookingBinding.inflate(layoutInflater)

        val runningTimeArray = resources.getStringArray(R.array.running_time_array)
        val runningTimeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, runningTimeArray)

        val reservationDayArray = resources.getStringArray(R.array.reservation_day_array)
        val reservationDayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, reservationDayArray)

        binding.timePickerActions.adapter = runningTimeAdapter
        binding.datePickerActions.adapter = reservationDayAdapter

        binding.timePickerActions.setSelection(0)
        binding.datePickerActions.setSelection(0)

        val runningDateTime = binding.timePickerActions.selectedItem as String
        val reservationDay = binding.datePickerActions.selectedItem as String


        binding.plusButton.setOnClickListener {
            binding.count.text = binding.count.text.toString()
                .toIntOrNull()
                ?.plus(1)
                ?.toString()
                ?: "1"
        }


        binding.minusButton.setOnClickListener {
            if (binding.count.text.toString().toInt() <= 1) {
                Toast.makeText(this, "1명 이상의 인원을 선택해야 합니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.count.text = binding.count.text.toString()
                .toIntOrNull()
                ?.minus(1)
                ?.toString()
                ?: "1"
        }

        binding.commonButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    navigateToReservationComplete(
                        BookingStatus(
                            movie = movie(),
                            isBooked = true,
                            memberCount = MemberCount(binding.count.text.toString().toInt()),
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


        binding.bookedMovieRunningDayText.text = binding.bookedMovieRunningDayText.context.getString(
            R.string.movie_screening_date,
            movie.startDateTime,
            movie.endDateTime
        )
        binding.bookedMovieTitleText.text = movie.title
        binding.bookedMovieRunningTimeText.text = binding.bookedMovieRunningTimeText.context.getString(
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
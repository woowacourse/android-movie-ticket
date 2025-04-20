package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie

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

        val title: TextView = findViewById(R.id.movie_title)
        val poster: ImageView = findViewById(R.id.movie_poster)
        val screeningDate: TextView = findViewById(R.id.movie_screening_date)
        val runningTimes: TextView = findViewById(R.id.movie_running_time)

        title.text = movie.title
        poster.setImageResource(movie.poster)
        screeningDate.text = screeningDate.context.getString(
            R.string.movie_screening_date,
            movie.screeningStartDate.toDotFormat(),
            movie.screeningEndDate.toDotFormat(),
        )
        runningTimes.text = runningTimes.context.getString(
            R.string.movie_running_time,
            movie.runningTime,
        )

//        runningTimePosition = savedInstanceState?.getInt("runningTime") ?: 0
//        datePosition = savedInstanceState?.getInt("reservationDay") ?: 0

//        binding.datePickerActions.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    reservationDay = parent?.getItemAtPosition(position) as LocalDate
//                    binding.timePickerActions.adapter = RunningTimeSpinnerAdapter(
//                        RunningTimes().runningTimes(reservationDay)
//                    )
//                    datePosition = position
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//            }
//
//        binding.timePickerActions.adapter = RunningTimeSpinnerAdapter(
//            RunningTimes().runningTimes(reservationDay)
//        )
//        binding.timePickerActions.setSelection(runningTimePosition)
//        runningDateTime = binding.timePickerActions.selectedItem as LocalTime
//        binding.timePickerActions.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    runningDateTime = parent?.getItemAtPosition(position) as LocalTime
//                    runningTimePosition = position
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//            }
//
//        binding.plusButton.setOnClickListener {
//            binding.count.text = binding.count.text.toString()
//                .toIntOrNull()
//                ?.plus(1)
//                ?.toString()
//                ?: "1"
//        }
//
//        binding.minusButton.setOnClickListener {
//            if (memberCount.toString().toInt() <= 1) {
//                Toast.makeText(this, "1명 이상의 인원을 선택해야 합니다", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            binding.count.text = binding.count.text.toString()
//                .toIntOrNull()
//                ?.minus(1)
//                ?.toString()
//                ?: "1"
//        }
//
//        binding.commonButton.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("예매 확인")
//                .setMessage("정말 예매하시겠습니까?")
//                .setPositiveButton("예매 완료") { _, _ ->
//                    navigateToReservationComplete(
//                        BookingStatus(
//                            movie = movie(),
//                            isBooked = true,
//                            memberCount = MemberCount(binding.count.text.toString().toInt()),
//                            bookedTime = LocalDateTime.of(reservationDay, runningDateTime)
//                        )
//                    )
//                }
//                .setNegativeButton("취소") { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .show()
//                .setCancelable(false)
//        }
//
//
//        binding.bookedMovieRunningDayText.text =
//            binding.bookedMovieRunningDayText.context.getString(
//                R.string.movie_screening_date,
//                movie.screeningStartDate,
//                movie.screeningEndDate
//            )
//        binding.bookedMovieTitleText.text = movie.title
//        binding.bookedMovieRunningTimeText.text =
//            binding.bookedMovieRunningTimeText.context.getString(
//                R.string.movie_running_time,
//                movie.runningTime
//            )
    }

    private fun navigateToReservationComplete(
        bookingStatus: BookingStatus
    ) {
        val intent = Intent(this, ReservationCompleteActivity::class.java)
            .apply { putExtra("bookingStatus", bookingStatus) }
        startActivity(intent)
    }

    private fun movie(): Movie {
        return BuildVersion().movie(intent)
    }
}

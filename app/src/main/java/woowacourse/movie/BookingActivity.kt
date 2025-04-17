package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Booking
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.collections.indexOf

class BookingActivity : AppCompatActivity() {
    //    private lateinit var date: String
//    private lateinit var time: String
    private var date: String = ""
    private var time: String = ""
    private var headCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val savedCount = savedInstanceState?.getInt("HEAD_COUNT")
        val savedScreeningDate = savedInstanceState?.getString("SCREENING_DATE")
        val savedScreeningTime = savedInstanceState?.getString("SCREENING_TIME")

        date = savedScreeningDate ?: LocalDate.now().toString()
        time = savedScreeningTime ?: LocalTime.now().toString()
        headCount = savedCount ?: 0

        val movieData = movieOrNull() ?: return

        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        bookingTitle.text = movieData.title
        val screeningStartDate = formatDate(movieData.screeningStartDate)
        val screeningEndDate = formatDate(movieData.screeningEndDate)
        bookingScreenDate.text =
            getString(R.string.screening_date_period, screeningStartDate, screeningEndDate)
        bookingRunningTime.text = getString(R.string.minute_text, movieData.runningTime)

        val booking = Booking(movieData)
//        val screeningDateAdapter = ScreeningDateAdapter(this)
//        val screeningTimeAdapter = ScreeningTimeAdapter(this)
//
//        screeningDateAdapter.setUpScreeningDateSpinner(booking.screeningPeriods())
//        val date = screeningDateAdapter.date
//        screeningTimeAdapter.setUpScreeningTimeSpinner(booking.screeningTimes(date))

        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        val screeningPeriods = booking.screeningPeriods()

        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningPeriods,
            )

        val position = screeningPeriods.indexOf(savedScreeningDate)
        if (position >= 0) {
            screeningDateSpinner.setSelection(position)
        }

        screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    date = screeningDateSpinner.getItemAtPosition(position).toString()
                    setupScreeningTimeSpinner(booking, savedScreeningTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    date = screeningDateSpinner.getItemAtPosition(0).toString()
                }
            }

        date = screeningDateSpinner.getItemAtPosition(0).toString()

        setupScreeningTimeSpinner(booking, savedScreeningTime)

        val btnMinus = findViewById<Button>(R.id.btn_minus)
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val peopleCount = findViewById<TextView>(R.id.tv_people_count)
        peopleCount.text = headCount.toString()

        btnMinus.setOnClickListener {
            if (headCount > 0) headCount--
            peopleCount.text = headCount.toString()
        }

        btnPlus.setOnClickListener {
            peopleCount.text = (++headCount).toString()
        }

        val btnReserveConfirm = findViewById<Button>(R.id.btn_reserve_confirm)
        btnReserveConfirm.setOnClickListener {
            // 인원수가 0이 아니고, 날짜와 시간을 선택한 경우에만 선택을 할 수 있도록 해야 함
            if (headCount > 0 && date.isNotBlank() && time.isNotBlank()) {
                val bookingResult = BookingResult(headCount, date, time)
                showConfirmDialog(movieData, bookingResult)
            }
        }
    }

    private fun setupScreeningTimeSpinner(
        booking: Booking,
        savedScreeningTime: String?,
    ) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)

        val screeningTimes = booking.screeningTimes(date)

        if (screeningTimes.isEmpty()) {
            // 다음 날짜 자동 선택
            val nextDate = LocalDate.parse(date).plusDays(1)
            date = nextDate.toString() // <-- 이게 핵심
            val nextTimes = booking.screeningTimes(date)

            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    nextTimes,
                )
            time = nextTimes.firstOrNull().orEmpty()
        } else {
            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    screeningTimes,
                )
        }
//        screeningTimeSpinner.adapter =
//            ArrayAdapter(
//                this,
//                android.R.layout.simple_spinner_item,
//                screeningTimes,
//            )

        val position = screeningTimes.indexOf(savedScreeningTime)
        if (position >= 0) {
            screeningTimeSpinner.setSelection(position)
        }

        screeningTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    time = screeningTimeSpinner.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    screeningTimeSpinner.getItemAtPosition(0).toString()
                }
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }

    private fun showConfirmDialog(
        movie: Movie,
        bookingResult: BookingResult,
    ) {
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                intent.putExtra("movieData", movie)
                intent.putExtra("bookingResult", bookingResult)
                startActivity(intent)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("HEAD_COUNT", headCount)
        outState.putString("SCREENING_DATE", date)
        outState.putString("SCREENING_TIME", time)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun movieOrNull(): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movieData", Movie::class.java)
        } else {
            intent.getParcelableExtra("movieData")
        }
    }
}

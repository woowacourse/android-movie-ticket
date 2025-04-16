package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import woowacourse.movie.model.Movie
import woowacourse.movie.model.ScreeningDateAdapter
import woowacourse.movie.model.ScreeningTimeAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    private lateinit var date: String
    //private lateinit var time: String
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        date = LocalDate.now().toString()

        val movieData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movieData", Movie::class.java)
            } else {
                intent.getParcelableExtra("movieData")
            } ?: return

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
        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                booking.screeningPeriods(),
            )

        screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    date = screeningDateSpinner.getItemAtPosition(position).toString()
                    setupScreeningTimeSpinner(booking)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    date = screeningDateSpinner.getItemAtPosition(0).toString()
                }
            }

        date = screeningDateSpinner.getItemAtPosition(0).toString()

        setupScreeningTimeSpinner(booking)

        val btnMinus = findViewById<Button>(R.id.btn_minus)
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        btnMinus.setOnClickListener {
            if (count > 0) count--
            peopleCount.text = count.toString()
        }

        btnPlus.setOnClickListener {
            peopleCount.text = (++count).toString()
        }

        val btnReserveConfirm = findViewById<Button>(R.id.btn_reserve_confirm)
        btnReserveConfirm.setOnClickListener {
            // 인원수가 0이 아니고, 날짜와 시간을 선택한 경우에만 선택을 할 수 있도록 해야 함
            if(count>0 && date.isNotBlank()){
                showConfirmDialog()
            }
        }
    }

    private fun setupScreeningTimeSpinner(booking: Booking) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        screeningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                booking.screeningTimes(date),
            )

        screeningTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    screeningTimeSpinner.getItemAtPosition(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    screeningTimeSpinner.getItemAtPosition(0).toString()
                }
            }
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }

    private fun showConfirmDialog(){
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _,_ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                //intent.putExtra("")
                startActivity(intent)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

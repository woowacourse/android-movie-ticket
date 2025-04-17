package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
import java.time.LocalDate

class BookingDetailActivity : AppCompatActivity() {
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var timeAdapter: TimeAdapter
    private lateinit var dateAdapter: DateAdapter
    private var ticketCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra(MOVIE_TITLE_KEY) ?: ""
        val startDate = intent.getStringExtra(MOVIE_START_DATE_KEY) ?: ""
        val endDate = intent.getStringExtra(MOVIE_END_DATE_KEY) ?: ""
        val runningTime = intent.getIntExtra(MOVIE_RUNNING_TIME_KEY, 0)
        val poster = intent.getIntExtra(MOVIE_POSTER_KEY, 0)

        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_detail_date).text = "$startDate ~ $endDate"
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text = "${runningTime}분"
        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(poster)

        setupDateSpinner(startDate, endDate)
        setupTimeSpinner(startDate)

        setupDateSpinnerItemClickListener()
        setupTicketCountClickListeners()

        setupSelectCompleteClickListener(title)
    }

    private fun setupSelectCompleteClickListener(title: String) {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            showSelectCompleteDialog(title)
        }
    }

    private fun showSelectCompleteDialog(title: String) {
        AlertDialog
            .Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                navigateToBookingComplete(title)
            }.setNegativeButton("취소", null)
            .setCancelable(false)
            .show()
    }

    private fun navigateToBookingComplete(title: String) {
        val selectedDate = dateSpinner.selectedItem.toString()
        val selectedTime = timeSpinner.selectedItem.toString()

        val intent =
            BookingCompleteActivity.newIntent(
                context = this,
                title = title,
                date = selectedDate,
                time = selectedTime,
                ticketCount = ticketCount,
            )

        startActivity(intent)
        finish()
    }

    private fun setupTicketCountClickListeners() {
        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            decreaseTicketCount()
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            increaseTicketCount()
        }
    }

    private fun decreaseTicketCount() {
        if (ticketCount > 0) {
            ticketCount--
            findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
        }
    }

    private fun increaseTicketCount() {
        ticketCount++
        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
    }

    private fun setupDateSpinnerItemClickListener() {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = parent?.getItemAtPosition(position) as LocalDate
                    val dateType = DateType.from(selectedDate)
                    timeAdapter.updateTimes(dateType)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupDateSpinner(
        startDate: String,
        endDate: String,
    ) {
        dateSpinner = findViewById<Spinner>(R.id.sp_booking_detail_date)

        dateAdapter =
            DateAdapter(
                this,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
            )
        dateSpinner.adapter = dateAdapter
    }

    private fun setupTimeSpinner(startDate: String) {
        timeSpinner = findViewById<Spinner>(R.id.sp_booking_detail_time)

        timeAdapter = TimeAdapter(this)
        timeAdapter.updateTimes(DateType.from(LocalDate.parse(startDate)))
        timeSpinner.adapter = timeAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(TICKET_DATE_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(TICKET_TIME_KEY, timeSpinner.selectedItemPosition)
        outState.putInt(TICKET_COUNT_KEY, ticketCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val ticketDate = savedInstanceState.getInt(TICKET_DATE_KEY)
        val ticketTime = savedInstanceState.getInt(TICKET_TIME_KEY)
        ticketCount = savedInstanceState.getInt(TICKET_COUNT_KEY)

        dateSpinner.setSelection(ticketDate)

        val selectedDate = DateType.from(LocalDate.parse(dateSpinner.selectedItem.toString()))
        timeAdapter.updateTimes(selectedDate)

        timeSpinner.setSelection(ticketTime)

        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
    }

    companion object {
        const val MOVIE_TITLE_KEY = "movie_title"
        const val MOVIE_START_DATE_KEY = "movie_start_date"
        const val MOVIE_END_DATE_KEY = "movie_end_date"
        const val MOVIE_RUNNING_TIME_KEY = "movie_running_time"
        const val MOVIE_POSTER_KEY = "movie_poster"
        const val TICKET_DATE_KEY = "ticket_date"
        const val TICKET_TIME_KEY = "ticket_time"
        const val TICKET_COUNT_KEY = "ticket_count"

        fun newIntent(
            context: Context,
            title: String,
            startDate: String,
            endDate: String,
            runningTime: Int,
            poster: Int,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_TITLE_KEY, title)
                putExtra(MOVIE_START_DATE_KEY, startDate)
                putExtra(MOVIE_END_DATE_KEY, endDate)
                putExtra(MOVIE_RUNNING_TIME_KEY, runningTime)
                putExtra(MOVIE_POSTER_KEY, poster)
            }
    }
}

package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieScheduler
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val movieScheduler = MovieScheduler()
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_count) }
    private lateinit var reservation: Reservation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserve)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movie", Movie::class.java)
            } else {
                intent.getParcelableExtra("movie") as? Movie
            }

        if (movie == null) {
            finish()
        }

        initMovieInfo(movie!!)
        initDateSpinner(movie.screeningDate)
        val startDate =
            movieScheduler.getStartDate(
                movie.screeningDate.startDate,
                LocalDate.now(),
            )
        initTimeSpinner(startDate)

        reservation = savedInstanceState?.getSerializable("reservation") as? Reservation
            ?: Reservation(
                title = movie.title,
                count = 1,
                reservedTime = getSelectedDateTime(),
            )

        updateTicketCount()
        initButtonClickListeners()
    }

    private fun initMovieInfo(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        val formattedScreeningDate = formatting(movie.screeningDate)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formattedScreeningDate
        runningTime.text = MINUTE.format(movie.runningTime.time)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = movieScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectDate = dates[position]
                    initTimeSpinner(selectDate)
                    reservation = reservation.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun initTimeSpinner(selectedDate: LocalDate) {
        val currentDateTime = LocalDateTime.now()
        val times =
            movieScheduler.reservableTimes(
                selectedDate,
                currentDateTime,
            )

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    reservation = reservation.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun getSelectedDateTime(): LocalDateTime {
        return LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate?,
            timeSpinner.selectedItem as LocalTime,
        )
    }

    private fun initButtonClickListeners() {
        val minusBtn = findViewById<Button>(R.id.btn_minus)
        val plusBtn = findViewById<Button>(R.id.btn_plus)
        val selectBtn = findViewById<Button>(R.id.btn_select)

        minusBtn.setOnClickListener {
            reservation = reservation.minusCount()
            updateTicketCount()
        }

        plusBtn.setOnClickListener {
            reservation = reservation.addCount()
            updateTicketCount()
        }

        val alertDialog = initSelectDialog()

        selectBtn.setOnClickListener {
            alertDialog.show()
        }
    }

    private fun initSelectDialog(): AlertDialog.Builder {
        return AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                val intent =
                    Intent(this, ReservationResultActivity::class.java).apply {
                        putExtra("reservation", reservation)
                    }
                startActivity(intent)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
    }

    private fun updateTicketCount() {
        ticketCount.text = reservation.count.toString()
    }

    private fun formatting(screeningDate: ScreeningDate): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return SCREENING_DATE.format(start, end)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable("reservation", reservation)
    }

    companion object {
        private const val MINUTE = "%d분"
        private const val SCREENING_DATE = "%s ~ %s"
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}

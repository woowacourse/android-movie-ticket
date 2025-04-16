package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Movie
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {
    private var selectedDate: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("data", Movie::class.java)
            } else {
                intent.getSerializableExtra("data") as Movie
            }

        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        val poster =
            AppCompatResources.getDrawable(
                this,
                data?.poster ?: R.drawable.lalaland,
            )
        posterImageView.setImageDrawable(poster)

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_title)
        movieTitleTextView.text = data?.title

        val screeningDateTextView = findViewById<TextView>(R.id.tv_reservation_screening_date)
        val startDate = data?.startDate?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        val endDate = data?.endDate?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        screeningDateTextView.text =
            resources.getString(R.string.movie_screening_date, startDate, endDate)

        val runningTimeTextView = findViewById<TextView>(R.id.tv_reservation_running_time)
        val runningTime = data?.runningTime
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)

        setupDateAdapter(data!!)
        setupTimeAdapter()

        val peopleCount = findViewById<TextView>(R.id.tv_reservation_people_count)
        findViewById<Button>(R.id.btn_reservation_minus_people_count).setOnClickListener {
            if (peopleCount.text.toString().toInt() == 1) {
                Toast.makeText(this, "최소 1명은 선택해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            peopleCount.text = (peopleCount.text.toString().toInt() - 1).toString()
        }
        findViewById<Button>(R.id.btn_reservation_plus_people_count).setOnClickListener {
            peopleCount.text = (peopleCount.text.toString().toInt() + 1).toString()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDateAdapter(movie: Movie) {
        val duration = (movie.startDate..movie.endDate).toList()

        val dateAdapter =
            ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                duration,
            )

        findViewById<Spinner>(R.id.spinner_reservation_date).apply {
            adapter = dateAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        selectedDate = duration[position].toLocalDate()
                        setupTimeAdapter()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun setupTimeAdapter() {
        val now = LocalDateTime.now()
        val times: List<String>
        if (LocalDate.now() == selectedDate) {
            times =
                when (now.dayOfWeek) {
                    DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY ->
                        weekdayTime.timeTable(now.hour)

                    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekendTime.timeTable(now.hour)

                    null -> emptyList()
                }
        } else {
            times =
                when (selectedDate.dayOfWeek) {
                    DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY ->
                        weekdayTime.map { "$it:00" }

                    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekendTime.map { "$it:00" }
                    null -> emptyList()
                }
        }

        val timeAdapter =
            ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                times,
            )

        findViewById<Spinner>(R.id.spinner_reservation_time).adapter = timeAdapter
    }

    private fun ClosedRange<LocalDate>.toList(): List<String> {
        val dates = mutableListOf<String>()
        var current = LocalDate.now()
        while (current <= endInclusive) {
            dates.add(current.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
            current = current.plusDays(1)
        }
        return dates
    }

    private fun List<Int>.timeTable(nowHour: Int): List<String> {
        forEachIndexed { index, time ->
            if (time > nowHour) {
                return slice(index..<size).map { "$it:00" }
            }
        }
        return emptyList()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private val weekdayTime =
        listOf(
            10,
            12,
            14,
            16,
            18,
            20,
            22,
            24,
        )

    private val weekendTime =
        listOf(
            9,
            11,
            13,
            15,
            17,
            19,
            21,
            23,
        )

    private fun String.toLocalDate(): LocalDate {
        val year = slice(0..3).toInt()
        val month = slice(5..6).toInt()
        val date = slice(8..9).toInt()
        return LocalDate.of(year, month, date)
    }
}

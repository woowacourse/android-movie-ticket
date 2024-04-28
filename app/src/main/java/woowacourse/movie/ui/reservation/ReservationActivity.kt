package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.movie.ScreenTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.repository.DummyMovieList
import woowacourse.movie.repository.DummyScreenList
import woowacourse.movie.repository.ScreenListRepository
import woowacourse.movie.ui.seatselection.SeatSelectionActivity
import java.time.LocalDate

class ReservationActivity : AppCompatActivity() {
    private lateinit var reservationViewGroup: ReservationViewGroup
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var ticket: Ticket
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindViewGroup()
        ticket = Ticket(intent.getLongExtra("screenId", 0))
        setUpViews(intent.getLongExtra("screenId", 0), DummyScreenList)
        setButtonClickListeners()
        setUpSpinner(intent.getLongExtra("screenId", 0), DummyScreenList)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", reservationViewGroup.countTextView.text.toString().toInt())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt("count", 1)
            if (count != 1) {
                repeat(count - 1) {
                    ticket.addCount()
                }
            }
            reservationViewGroup.countTextView.text = count.toString()
        }
    }

    private fun setUpSpinner(
        screenId: Long,
        screenListRepository: ScreenListRepository,
    ) {
        dateSpinner = findViewById(R.id.reservation_screen_date_spinner)
        timeSpinner = findViewById(R.id.reservation_screen_time_spinner)

        val dateList = getDateList(screenId, screenListRepository)
        ticket.date = LocalDate.parse(dateList[0])

        val dateAdapter =
            ArrayAdapter(
                this@ReservationActivity,
                R.layout.date_spinner_item,
                dateList,
            )

        val timeList = ScreenTime(ticket.date).timeList()
        ticket.time = timeList[0]

        var timeAdapter =
            ArrayAdapter(
                this@ReservationActivity,
                R.layout.date_spinner_item,
                timeList,
            )
        dateSpinner.adapter = dateAdapter
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long,
                ) {
                    ticket.date = LocalDate.parse(dateSpinner.getItemAtPosition(p2) as String)
                    Log.d("dayOfWeek", "${ticket.date.dayOfWeek}")
                    val screenTime = ScreenTime(ticket.date).timeList()
                    Log.d("dayOfWeek", "${screenTime[0]}")

                    timeAdapter =
                        ArrayAdapter(
                            this@ReservationActivity,
                            R.layout.date_spinner_item,
                            screenTime,
                        )
                    timeSpinner.adapter = timeAdapter
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        timeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ticket.time =timeSpinner.getItemAtPosition(p2) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        timeSpinner.adapter = timeAdapter
    }

    private fun getDateList(
        screenId: Long,
        screenListRepository: ScreenListRepository,
    ): List<String> {
        val screenData = screenListRepository.findOrNull(screenId) as Screen?
        val dateList = mutableListOf<String>()
        if (screenData != null) {
            val movieData = DummyMovieList.findOrNull(id = screenData.movieId)
            if (movieData != null) {
                // 수정 필요
                title = movieData.title

                ticket.date = movieData.screenPeriod[0]
                var date = movieData.screenPeriod[0]
                dateList.add(date.toString())

                while (date != movieData.screenPeriod[1]) {
                    date = date.plusDays(1)
                    dateList.add(date.toString())
                }
            }
        }
        return dateList
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun bindViewGroup() {
        reservationViewGroup =
            ReservationViewGroup(
                imageView = findViewById(R.id.reservation_imageview),
                titleTextView = findViewById(R.id.reservation_title_textview),
                screenDateTextView = findViewById(R.id.reservation_screen_date_textview),
                runningTimeTextView = findViewById(R.id.reservation_running_time_textview),
                descriptionTextView = findViewById(R.id.reservation_description),
                countTextView = findViewById(R.id.reservation_count_textview),
                addButton = findViewById(R.id.reservation_add_button),
                subButton = findViewById(R.id.reservation_sub_button),
                reservationCompleteButton = findViewById(R.id.reservation_complete_button),
            )
    }

    private fun setUpViews(
        screenId: Long,
        screenListRepository: ScreenListRepository,
    ) {
        reservationViewGroup.updateWithScreenId(screenId, screenListRepository)
        updateTicketCount()
    }

    private fun updateTicketCount() {
        reservationViewGroup.countTextView.text = ticket.count.toString()
    }

    private fun setButtonClickListeners() {
        reservationViewGroup.addButton.setOnClickListener {
            ticket.addCount()
            updateTicketCount()
        }

        reservationViewGroup.subButton.setOnClickListener {
            ticket.subCount()
            updateTicketCount()
        }

        reservationViewGroup.reservationCompleteButton.setOnClickListener {
            val intent = Intent(this, SeatSelectionActivity::class.java)
            intent.putExtra("screenTitle", title)
            intent.putExtra("ticket", ticket)

            this.startActivity(intent)
        }
    }
}

class ReservationViewGroup(
    val imageView: ImageView,
    val titleTextView: TextView,
    val screenDateTextView: TextView,
    val runningTimeTextView: TextView,
    val descriptionTextView: TextView,
    val countTextView: TextView,
    val addButton: Button,
    val subButton: Button,
    val reservationCompleteButton: Button,
) {
    fun updateWithScreenId(
        screenId: Long,
        screenListRepository: ScreenListRepository,
    ) {
        val screenData = screenListRepository.findOrNull(screenId) as Screen?
        if (screenData != null) {
            val movieData = DummyMovieList.findOrNull(id = screenData.movieId)
            if (movieData != null) {
                initImageView(movieData)
                initTitleTextView(movieData)
                initScreenDateTextView(movieData)
                initRunningTimeTextView(movieData)
                initDescriptionTextView(movieData)
            }
        }
    }

    private fun initImageView(movie: Movie) {
        imageView.setImageResource(movie.imgResId)
    }

    private fun initTitleTextView(movie: Movie) {
        titleTextView.text = movie.title
    }

    private fun initScreenDateTextView(movie: Movie) {
        screenDateTextView.text = movie.screenPeriodToString()
    }

    private fun initRunningTimeTextView(movie: Movie) {
        runningTimeTextView.text = movie.runningTime.toString()
    }

    private fun initDescriptionTextView(movie: Movie) {
        descriptionTextView.text = movie.description
    }
}

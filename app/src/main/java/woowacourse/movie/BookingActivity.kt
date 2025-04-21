package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.SpinnerAdapter
import woowacourse.movie.domain.MovieInfoGetter
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.dto.Ticket

class BookingActivity : AppCompatActivity() {
    private lateinit var movieInfo: MovieInfo
    private lateinit var movieTime: Spinner
    private lateinit var selectedDate: Spinner
    private lateinit var ticketCount: TextView
    private lateinit var movieDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        selectedDate = findViewById(R.id.selected_date)
        movieTime = findViewById(R.id.movie_time)
        ticketCount = findViewById(R.id.ticket_count)
        movieDate = findViewById(R.id.movie_date)

        setupPage()
        setupDateChangeListener()
        countButtonHandler()
        confirmButtonHandler()
        if (savedInstanceState != null) {
            repairInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_TICKET_COUNT, ticketCount.text.toString())
        outState.putInt(KEY_MOVIE_DATE_POSITION, selectedDate.selectedItemPosition)
        outState.putInt(KEY_MOVIE_TIME_POSITION, movieTime.selectedItemPosition)
    }

    private fun repairInstanceState(state: Bundle) {
        selectedDate.setSelection(state.getInt(KEY_MOVIE_DATE_POSITION))
        selectedDate.post {
            movieTime.post {
                movieTime.setSelection(state.getInt(KEY_MOVIE_TIME_POSITION))
            }
        }
        ticketCount.text = state.getString(KEY_TICKET_COUNT)
    }

    private fun setupPage() {
        val title = findViewById<TextView>(R.id.title)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val poster = findViewById<ImageView>(R.id.movie_poster)

        movieInfo = intent.getParcelableExtra(KEY_MOVIE_INFO) ?: MovieInfo(
            R.drawable.sample_poster,
            "샘플입니다",
            "2024-1-1",
            "2025-1-1",
            99,
        )

        movieInfo.let { info ->
            poster.setImageResource(info.poster)
            title.text = info.title
            movieDate.text =
                resources.getString(
                    R.string.movie_date,
                    info.startDate,
                    info.endDate,
                )
            runningTime.text = resources.getString(R.string.running_time, info.runningTime)

            SpinnerAdapter.bind(this, selectedDate, MovieInfoGetter.getDates(info.startDate, info.endDate))
            SpinnerAdapter.bind(this, movieTime, MovieInfoGetter.getTimes(info.startDate))
        }
    }

    private fun setupDateChangeListener() {
        selectedDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    movieInfo.let { info ->
                        val selectedDate = MovieInfoGetter.getDates(info.startDate, info.endDate).getOrNull(position)
                        selectedDate?.let {
                            val selectedTimes = MovieInfoGetter.getTimes(it)
                            SpinnerAdapter.bind(this@BookingActivity, movieTime, selectedTimes)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun confirmButtonHandler() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)

        confirmButton.setOnClickListener {
            if (ticketCount.text.toString() == "0") return@setOnClickListener
            askToConfirmBooking()
        }
    }

    private fun countButtonHandler() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() - 1).toString()
            if (ticketCount.text.toString().toInt() < 0) ticketCount.text = "0"
        }

        plusButton.setOnClickListener {
            ticketCount.text = (ticketCount.text.toString().toInt() + 1).toString()
        }
    }

    private fun askToConfirmBooking() {
        val ticket =
            Ticket(
                title = findViewById<TextView>(R.id.title).text.toString(),
                date = selectedDate.selectedItem.toString(),
                time = movieTime.selectedItem.toString(),
                count = ticketCount.text.toString(),
                money = (ticketCount.text.toString().toInt() * TICKET_PRICE).toString(),
            )

        ConfirmDialog.show(this, ticket) {
            val intent =
                Intent(this, BookingResultActivity::class.java).apply {
                    putExtra(KEY_TICKET, ticket)
                }
            startActivity(intent)
        }
    }

    companion object {
        private const val KEY_TICKET_COUNT = "TICKET_COUNT"
        private const val KEY_MOVIE_DATE_POSITION = "MOVIE_DATE_POSITION"
        private const val KEY_MOVIE_TIME_POSITION = "MOVIE_TIME_POSITION"
        private const val KEY_MOVIE_INFO = "MOVIE_INFO"
        private const val KEY_TICKET = "TICKET"

        private const val TICKET_PRICE = 13000
    }
}

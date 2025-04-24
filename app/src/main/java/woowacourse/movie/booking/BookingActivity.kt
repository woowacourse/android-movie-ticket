package woowacourse.movie.booking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.bookingResult.BookingResultActivity
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.dto.Ticket
import woowacourse.movie.util.DataUtils

class BookingActivity : AppCompatActivity() {
    private lateinit var movieInfo: MovieInfo
    private lateinit var movieTime: Spinner
    private lateinit var selectedDate: Spinner
    private lateinit var ticketCount: TextView
    private lateinit var movieDate: TextView
    private var ticketCountValue = TicketCount()

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

        movieInfo = DataUtils.getExtraOrFinish<MovieInfo>(intent, this, KEY_MOVIE_INFO) ?: return

        poster.setImageResource(movieInfo.poster)
        title.text = movieInfo.title
        movieDate.text =
            this.getString(
                R.string.movie_date,
                movieInfo.startDate,
                movieInfo.endDate,
            )
        runningTime.text = this.getString(R.string.running_time, movieInfo.runningTime)

        SpinnerAdapter.bind(
            this,
            selectedDate,
            MovieInfoGetter.getDates(movieInfo.startDate, movieInfo.endDate),
        )
        SpinnerAdapter.bind(this, movieTime, MovieInfoGetter.getTimes(movieInfo.startDate))
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
                    val selectedDate =
                        MovieInfoGetter
                            .getDates(movieInfo.startDate, movieInfo.endDate)
                            .getOrNull(position)
                    selectedDate?.let {
                        val selectedTimes = MovieInfoGetter.getTimes(it)
                        SpinnerAdapter.bind(this@BookingActivity, movieTime, selectedTimes)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun confirmButtonHandler() {
        val confirmButton = findViewById<Button>(R.id.confirm_button)

        confirmButton.setOnClickListener {
            if (ticketCount.text.toString() == "0") return@setOnClickListener
            askToConfirmBook()
        }
    }

    private fun countButtonHandler() {
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)

        minusButton.setOnClickListener {
            ticketCountValue.downCount()
            ticketCount.text = ticketCountValue.count.toString()
        }

        plusButton.setOnClickListener {
            ticketCountValue.upCount()
            ticketCount.text = ticketCountValue.count.toString()
        }
    }

    private fun askToConfirmBook() {
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

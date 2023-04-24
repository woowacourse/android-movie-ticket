package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.moviebookingcheckactivity.MovieBookingCheckActivity
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieBookingInputView(private val view: ViewGroup) {
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    val ticketCount = woowacourse.movie.domain.price.TicketCount(1)

    init {
        initTicketCountView()
        initDateSpinner()
        initPlusButtonClickListener()
        initMinusButtonClickListener()
    }

    fun initTimeSpinner(): Spinner {
        timeSpinner = view.findViewById(R.id.spinner_time)
        return timeSpinner
    }

    private fun initTicketCountView() {
        tvTicketCount = view.findViewById(R.id.tv_ticket_count)
    }

    private fun initDateSpinner() {
        dateSpinner = view.findViewById(R.id.spinner_date)
    }

    fun initSpinnerAdapter(movieData: MovieUIModel, timeSpinnerRecoverState: Int) {
        timeSpinnerAdapter =
            TimeSpinnerAdapter(
                timeSpinner,
                woowacourse.movie.domain.datetime.ScreeningPeriod(
                    movieData.screeningStartDay,
                    movieData.screeningEndDay
                ),
                timeSpinnerRecoverState,
                view.context
            )
        dateSpinnerAdapter =
            DateSpinnerAdapter(
                dateSpinner,
                timeSpinnerAdapter::updateTimeTable,
                woowacourse.movie.domain.datetime.ScreeningPeriod(
                    movieData.screeningStartDay,
                    movieData.screeningEndDay
                ),
                view.context
            )
    }

    fun initTicketCount() {
        tvTicketCount.text = ticketCount.value.toString()
    }

    private fun initPlusButtonClickListener() {
        view.findViewById<Button>(R.id.btn_ticket_plus).setOnSingleClickListener {
            ticketCount.value++
            tvTicketCount.text = ticketCount.value.toString()
        }
    }

    private fun initMinusButtonClickListener() {
        view.findViewById<Button>(R.id.btn_ticket_minus).setOnSingleClickListener {
            ticketCount.value--
            if (ticketCount.value <= 1) ticketCount.value = 1
            tvTicketCount.text = ticketCount.value.toString()
        }
    }

    private fun getScreeningDateTime(movieData: MovieUIModel): woowacourse.movie.domain.datetime.ScreeningDateTime {
        val date = dateSpinner.selectedItem as LocalDate
        val time = timeSpinner.selectedItem as LocalTime

        return woowacourse.movie.domain.datetime.ScreeningDateTime(
            LocalDateTime.of(date, time),
            woowacourse.movie.domain.datetime.ScreeningPeriod(
                movieData.screeningStartDay,
                movieData.screeningEndDay
            )
        )
    }

    // timespinner 초기화 관련 방어코드 고려
    fun initBookingCompleteButtonClickListener(movieData: MovieUIModel) {
        view.findViewById<Button>(R.id.btn_booking_complete).setOnSingleClickListener {
            val intent = Intent(view.context, MovieSelectSeatActivity::class.java).apply {
                putExtra(MovieSelectSeatActivity.MOVIE_DATA, movieData)
                putExtra(MovieSelectSeatActivity.TICKET_COUNT, ticketCount.value)
                putExtra(
                    MovieSelectSeatActivity.BOOKED_SCREENING_DATE_TIME,
                    getScreeningDateTime(movieData).time
                )
            }
            view.context.startActivity(intent)
        }
    }
}

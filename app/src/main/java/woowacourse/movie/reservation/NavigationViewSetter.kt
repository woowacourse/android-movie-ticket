package woowacourse.movie.reservation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import domain.movie.ScreeningDate
import domain.reservation.TicketCount
import woowacourse.movie.R
import woowacourse.movie.model.DisplayItem
import woowacourse.movie.model.SeatSelectionInfo
import woowacourse.movie.reservation.ReservationActivity.Companion.SCREENING_DATE_POSITION_KEY
import woowacourse.movie.reservation.ReservationActivity.Companion.SCREENING_TIME_POSITION_KEY
import woowacourse.movie.reservation.ReservationActivity.Companion.TICKET_COUNT_KEY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class NavigationViewSetter(
    private val movieInfo: DisplayItem.MovieInfo,
    private val navigationBar: LinearLayout
) {
    private val ticketCountTextView = findViewConfiguration<TextView>(R.id.reservation_ticket_count_text_view)
    private val screeningDateSpinner = findViewConfiguration<Spinner>(R.id.screening_date_spinner)
    private val screeningTimeSpinner = findViewConfiguration<Spinner>(R.id.screening_time_spinner)

    fun setDateSpinner(savedInstanceState: Bundle?) {
        val defaultScreeningDatePosition =
            savedInstanceState?.getInt(SCREENING_DATE_POSITION_KEY) ?: 0
        val defaultScreeningTimePosition =
            savedInstanceState?.getInt(SCREENING_TIME_POSITION_KEY) ?: 0

        screeningDateSpinner.applyArrayAdapter(movieInfo.screeningPeriod)
        screeningDateSpinner.onItemSelectedListener = SpinnerItemSelectedListener(
            defaultScreeningTimePosition,
            ::onScreeningDateSelected
        )
        screeningDateSpinner.setSelection(defaultScreeningDatePosition)
    }

    fun setMinusButtonClickedListener(alertError: () -> Unit) {
        val minusButton = findViewConfiguration<TextView>(R.id.reservation_ticket_count_minus_button)

        minusButton.setOnClickListener {
            runCatching {
                val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() - 1)
                ticketCountTextView.text = ticketCount.value.toString()
            }.onFailure {
                alertError()
            }
        }
    }

    fun setPlusButtonClickedListener() {
        val plusButton = findViewConfiguration<TextView>(R.id.reservation_ticket_count_plus_button)

        plusButton.setOnClickListener {
            val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() + 1)
            ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    fun setOnCompleteButtonClickedListener(onCompleted: (seatSelectionInfo: SeatSelectionInfo) -> Unit) {
        val completeButton = findViewConfiguration<Button>(R.id.reservation_complete_button)

        completeButton.setOnClickListener {
            val seatCount = ticketCountTextView
                .text
                .toString()
                .toInt()
            val reservation = SeatSelectionInfo(
                movieName = movieInfo.movieName,
                screeningTime = LocalDateTime.of(
                    screeningDateSpinner.selectedItem as LocalDate,
                    screeningTimeSpinner.selectedItem as LocalTime
                ),
                seatCount = seatCount
            )

            onCompleted(reservation)
        }
    }

    fun setTicketCountTextView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            ticketCountTextView.text = TicketCount.MINIMUM.toString()
            return
        }
        val ticketCount: Int = savedInstanceState.getInt(TICKET_COUNT_KEY)
        ticketCountTextView.text = ticketCount.toString()
    }

    fun saveState(outState: Bundle) {
        outState.putInt(TICKET_COUNT_KEY, ticketCountTextView.text.toString().toInt())
        outState.putInt(SCREENING_DATE_POSITION_KEY, screeningDateSpinner.selectedItemPosition)
        outState.putInt(SCREENING_TIME_POSITION_KEY, screeningTimeSpinner.selectedItemPosition)
    }

    private fun onScreeningDateSelected(date: ScreeningDate?, defaultPosition: Int = 0) {
        val times = date?.screeningTimes ?: listOf()

        screeningTimeSpinner.applyArrayAdapter(times)
        screeningTimeSpinner.setSelection(defaultPosition)
    }

    private fun <T : View?> findViewConfiguration(resourceId: Int): T {
        return navigationBar.findViewById<T>(resourceId)
    }

    private fun <T> Spinner.applyArrayAdapter(inputData: List<T>) {
        this.adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            inputData
        )
    }
}

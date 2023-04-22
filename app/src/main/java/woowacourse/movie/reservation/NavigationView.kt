package woowacourse.movie.reservation

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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class NavigationView(
    private val movieInfo: DisplayItem.MovieInfo,
    private val navigationBar: LinearLayout
) {
    private val ticketCountTextView =
        findViewConfiguration<TextView>(R.id.reservation_ticket_count_text_view)
    private val screeningDateSpinner = findViewConfiguration<Spinner>(R.id.screening_date_spinner)
    private val screeningTimeSpinner = findViewConfiguration<Spinner>(R.id.screening_time_spinner)

    val state: NavigationViewState
        get() = NavigationViewState(
            ticketCountTextView.text.toString().toInt(),
            screeningDateSpinner.selectedItemPosition,
            screeningTimeSpinner.selectedItemPosition
        )

    fun setDateSpinner(
        defaultScreeningDatePosition: Int = 0,
        defaultScreeningTimePosition: Int = 0
    ) {
        screeningDateSpinner.applyArrayAdapter(movieInfo.screeningPeriod)
        screeningDateSpinner.onItemSelectedListener = SpinnerItemSelectedListener(
            defaultScreeningTimePosition,
            ::onScreeningDateSelected
        )
        screeningDateSpinner.setSelection(defaultScreeningDatePosition)
    }

    fun setMinusButtonClickedListener(alertError: () -> Unit) {
        val minusButton =
            findViewConfiguration<TextView>(R.id.reservation_ticket_count_minus_button)

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

    fun setTicketCountTextView(ticketCount: Int = 1) {
        ticketCountTextView.text = ticketCount.toString()
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

package woowacourse.movie.view.moviedetail

import android.content.Context
import android.widget.ArrayAdapter
import com.example.domain.DayOfWeek
import com.example.domain.screeningschedule.ReservationDate
import com.example.domain.screeningschedule.ReservationTime
import java.time.LocalDate

class SpinnerAdapter(private val context: Context) {

    fun getDateSpinnerAdapter(startDate: LocalDate, endDate: LocalDate): ArrayAdapter<String> {
        val dateAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            ReservationDate(startDate, endDate).getIntervalDays(),
        )
        dateAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        return dateAdapter
    }

    fun getTimeSpinnerAdapter(selectedDay: LocalDate): ArrayAdapter<String> {
        val timeAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            ReservationTime(DayOfWeek.checkDayOfWeek(selectedDay)).getIntervalTimes(),
        )

        timeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        return timeAdapter
    }
}

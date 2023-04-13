package woowacourse.movie

import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.domain.DayOfWeek
import woowacourse.movie.domain.ReservationDate
import woowacourse.movie.domain.ReservationTime
import woowacourse.movie.domain.RunningDate
import java.time.LocalDate

class SpinnerAdapter(val context: Context) {

    fun getDateSpinnerAdapter(date: RunningDate): ArrayAdapter<String> {
        val dateAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            ReservationDate(date).getScreeningDays(),
        )
        dateAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        return dateAdapter
    }

    fun getTimeSpinnerAdapter(selectedDay: LocalDate): ArrayAdapter<CharSequence> {
        val timeAdapter = ArrayAdapter.createFromResource(
            context,
            ReservationTime(DayOfWeek.checkDayOfWeek(selectedDay)).getScreeningTimes(),
            android.R.layout.simple_spinner_item,
        )

        timeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        return timeAdapter
    }
}

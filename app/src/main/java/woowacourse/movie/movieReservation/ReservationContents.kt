package woowacourse.movie.movieReservation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import model.ReservationModel
import model.ScreeningModel
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class ReservationContents(private val view: View) {
    private val posterView: ImageView = view.findViewById(R.id.reservation_movie_poster)
    private val titleView: TextView = view.findViewById(R.id.reservation_movie_title)
    private val screeningDateView: TextView = view.findViewById(R.id.reservation_movie_release_date)
    private val runTimeView: TextView = view.findViewById(R.id.reservation_movie_running_time)
    private val summaryView: TextView = view.findViewById(R.id.reservation_movie_summary)

    fun update(screeningModel: ScreeningModel) {
        posterView.setImageResource(screeningModel.poster)
        titleView.text = screeningModel.title
        screeningDateView.text = getScreeningDate(screeningModel.reservationModel)
        runTimeView.text = view.context.getString(R.string.movie_running_time).format(screeningModel.runTime)
        summaryView.text = screeningModel.summary
    }

    private fun getScreeningDate(reservationModel: ReservationModel): String {
        return "${reservationModel.startDate.format(dateTimeFormatter)} ~ ${reservationModel.endDate.format(dateTimeFormatter)}"
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}

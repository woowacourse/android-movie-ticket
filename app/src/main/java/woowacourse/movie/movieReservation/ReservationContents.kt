package woowacourse.movie.movieReservation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import model.ScreeningModel
import woowacourse.movie.R

class ReservationContents(private val view: View) {
    private val posterView: ImageView = view.findViewById(R.id.reservation_movie_poster)
    private val titleView: TextView = view.findViewById(R.id.reservation_movie_title)
    private val releaseDataView: TextView = view.findViewById(R.id.reservation_movie_release_date)
    private val runningTimeView: TextView = view.findViewById(R.id.reservation_movie_running_time)
    private val summaryView: TextView = view.findViewById(R.id.reservation_movie_summary)

    fun update(screeningModel: ScreeningModel) {
        posterView.setImageResource(screeningModel.poster)
        titleView.text = screeningModel.title
        releaseDataView.text = screeningModel.getReserveDateRange()
        runningTimeView.text = view.context.getString(R.string.movie_running_time).format(screeningModel.runningTime)
        summaryView.text = screeningModel.summary
    }
}

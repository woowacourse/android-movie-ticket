package woowacourse.movie.movieReservation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import entity.Screening
import woowacourse.movie.R

class MovieReservationContents(
    private val posterView: ImageView,
    private val titleView: TextView,
    private val releaseDataView: TextView,
    private val runningTimeView: TextView,
    private val summaryView: TextView,
) {
    fun bind(context: Context, screening: Screening) {
        posterView.setImageResource(screening.poster)
        titleView.text = screening.title
        releaseDataView.text = screening.getReserveDateRange()
        runningTimeView.text = context.getString(R.string.movie_running_time).format(screening.runningTime)
        summaryView.text = screening.summary
    }
}

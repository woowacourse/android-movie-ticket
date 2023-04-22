package woowacourse.movie.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.utils.DateUtil

class MovieViewHolder(
    private val view: View,
) {
    private val movieImageView = view.findViewById<ImageView>(R.id.movie_poster)
    private val movieTitleView = view.findViewById<TextView>(R.id.movie_title)
    private val movieReleaseDateView = view.findViewById<TextView>(R.id.movie_release_date)
    private val movieRunningTimeView = view.findViewById<TextView>(R.id.movie_running_time)
    private val movieReservationButton = view.findViewById<Button>(R.id.movie_reservation_button)

    fun bind(movieScheduleUi: MovieModelUi.MovieScheduleUi, onClick: (MovieModelUi.MovieScheduleUi) -> Unit) {
        movieImageView.setImageResource(movieScheduleUi.poster)
        movieTitleView.text = movieScheduleUi.title
        movieReleaseDateView.text = DateUtil(view.context).getDateRange(movieScheduleUi.startDate, movieScheduleUi.endDate)
        movieRunningTimeView.text = view.context.getString(R.string.movie_running_time).format(movieScheduleUi.runningTime)
        movieReservationButton.setOnClickListener { onClick(movieScheduleUi) }
    }
}

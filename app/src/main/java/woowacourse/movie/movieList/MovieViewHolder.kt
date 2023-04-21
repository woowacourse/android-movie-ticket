package woowacourse.movie.movieList

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
    fun bind(movieScheduleUi: MovieModelUi.MovieScheduleUi, onClick: (MovieModelUi.MovieScheduleUi) -> Unit) {
        view.findViewById<ImageView>(R.id.movie_poster).setImageResource(movieScheduleUi.poster)
        view.findViewById<TextView>(R.id.movie_title).text = movieScheduleUi.title
        view.findViewById<TextView>(R.id.movie_release_date).text = DateUtil(view.context).getDateRange(movieScheduleUi.startDate, movieScheduleUi.endDate)
        view.findViewById<TextView>(R.id.movie_running_time).text = view.context.getString(R.string.movie_running_time).format(movieScheduleUi.runningTime)
        view.findViewById<Button>(R.id.movie_reservation_button).setOnClickListener { onClick(movieScheduleUi) }
    }
}

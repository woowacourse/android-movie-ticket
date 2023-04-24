package woowacourse.movie.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.utils.DateUtil

class MovieViewHolder(
    val view: View,
    val onItemViewClick: (MovieModelUi.MovieScheduleUi) -> Unit,
) : RecyclerView.ViewHolder(view) {

    private val movieImageView: ImageView = view.findViewById(R.id.movie_poster)
    private val movieTitleView: TextView = view.findViewById(R.id.movie_title)
    private val movieReleaseDateView: TextView = view.findViewById(R.id.movie_release_date)
    private val movieRunningTimeView: TextView = view.findViewById(R.id.movie_running_time)
    private val movieReservationButton: Button =
        view.findViewById(R.id.movie_reservation_button)

    fun bind(item: MovieModelUi.MovieScheduleUi) {
        movieImageView.setImageResource(item.poster)
        movieTitleView.text = item.title
        movieReleaseDateView.text = DateUtil(view.context).getDateRange(item.startDate, item.endDate)
        movieRunningTimeView.text = view.context.getString(R.string.movie_running_time).format(item.runningTime)
        movieReservationButton.setOnClickListener { onItemViewClick(item) }
    }
}

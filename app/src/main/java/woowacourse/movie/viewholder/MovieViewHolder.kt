package woowacourse.movie.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.utils.DateUtil

class MovieViewHolder(
    private val context: Context,
) : LinearLayout(context) {

    private val movieImageView: ImageView
    private val movieTitleView: TextView
    private val movieReleaseDateView: TextView
    private val movieRunningTimeView: TextView
    private val movieReservationButton: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.item_movie_list, this)
        movieImageView = findViewById(R.id.movie_poster)
        movieTitleView = findViewById(R.id.movie_title)
        movieReleaseDateView = findViewById(R.id.movie_release_date)
        movieRunningTimeView = findViewById(R.id.movie_running_time)
        movieReservationButton = findViewById(R.id.movie_reservation_button)
    }

    fun bind(movieScheduleUi: MovieModelUi.MovieScheduleUi, onClick: (MovieModelUi.MovieScheduleUi) -> Unit) {
        movieImageView.setImageResource(movieScheduleUi.poster)
        movieTitleView.text = movieScheduleUi.title
        movieReleaseDateView.text = DateUtil(context).getDateRange(movieScheduleUi.startDate, movieScheduleUi.endDate)
        movieRunningTimeView.text = context.getString(R.string.movie_running_time).format(movieScheduleUi.runningTime)
        movieReservationButton.setOnClickListener { onClick(movieScheduleUi) }
    }
}

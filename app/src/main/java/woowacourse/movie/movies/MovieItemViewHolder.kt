package woowacourse.movie.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieItemViewHolder(
    view: View,
) {
    private val title: TextView = view.findViewById(R.id.tv_movie_title)
    private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    private val date: TextView = view.findViewById(R.id.tv_movie_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val bookingBtn: Button = view.findViewById(R.id.btn_movie_booking)

    fun bind(
        movie: MovieUiModel,
        onBookingClick: (MovieUiModel) -> Unit,
    ) {
        title.text = movie.title
        poster.setImageResource(movie.posterResId)
        date.text = movie.periodText
        runningTime.text = movie.runningTimeText
        bookingBtn.setOnClickListener {
            onBookingClick(movie)
        }
    }
}

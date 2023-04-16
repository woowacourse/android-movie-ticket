package woowacourse.movie.adapter

import android.widget.ImageView
import android.widget.TextView
import java.time.format.DateTimeFormatter

data class MovieItemViewHolder(
    val movieMovieNameTextView: TextView,
    val dateFormat: DateTimeFormatter,
    val movieImageView: ImageView,
    val screeningDateTextView: TextView,
    val runningTimeTextView: TextView
)

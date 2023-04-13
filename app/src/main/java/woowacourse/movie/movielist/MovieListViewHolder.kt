package woowacourse.movie.movielist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieListViewHolder(itemView: View) {

    val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val screeningStartDate: TextView = itemView.findViewById(R.id.screening_start_date)
    val screeningEndDate: TextView = itemView.findViewById(R.id.screening_end_date)
    val runningTime: TextView = itemView.findViewById(R.id.running_time)
    val bookButton: Button = itemView.findViewById(R.id.book_button)
}

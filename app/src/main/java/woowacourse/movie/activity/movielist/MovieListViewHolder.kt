package woowacourse.movie.activity.movielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieListViewHolder(itemView: View) {

    val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val screeningDate: TextView = itemView.findViewById(R.id.movieList_screening_date)
    val runningTime: TextView = itemView.findViewById(R.id.movieList_running_time)
}

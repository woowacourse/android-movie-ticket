package woowacourse.movie.movieList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import woowacourse.movie.R

class MovieListViewHolder(view: View) {
    private val posterView: ImageView = view.findViewById(R.id.movie_poster)
    private val titleView: TextView = view.findViewById(R.id.movie_title)
    private val releaseDateView: TextView = view.findViewById(R.id.movie_release_date)
    private val runningTimeView: TextView = view.findViewById(R.id.movie_running_time)

    fun bind(
        @DrawableRes posterResource: Int,
        title: String,
        date: String,
        runningTime: String,
    ) {
        posterView.setImageResource(posterResource)
        titleView.text = title
        releaseDateView.text = date
        runningTimeView.text = runningTime
    }
}

package woowacourse.movie.activities.movielist.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieListViewHolder(val view: View) {
    private val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvDate: TextView = view.findViewById(R.id.tv_date)
    private val tvRunningTime: TextView = view.findViewById(R.id.tv_running_time)
    private val btnBook: Button = view.findViewById(R.id.btn_book)

    fun bind(item: Movie, onBookClick: (Movie) -> Unit) {
        with(item) {
            ivPoster.setImageResource(thumbnail)
            tvTitle.text = title
            tvDate.text = view.context.getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            tvRunningTime.text =
                view.context.getString(R.string.movie_running_time, runningTime)
            btnBook.setOnClickListener { onBookClick(item) }
        }
    }
}

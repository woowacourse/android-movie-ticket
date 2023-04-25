package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieItem
import woowacourse.movie.ui.formattedDate

class MovieListViewHolder(private val view: View, onBookClick: (Int) -> Unit) :
    ItemViewHolder(view) {
    private val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvDate: TextView = view.findViewById(R.id.tv_date)
    private val tvRunningTime: TextView = view.findViewById(R.id.tv_running_time)
    private val btnBook: Button = view.findViewById(R.id.btn_book)

    init {
        btnBook.setOnClickListener { onBookClick(adapterPosition) }
    }

    override fun bind(item: MovieItem) {
        with(item as MovieItem.MovieUI) {
            thumbnail?.let { ivPoster.setImageResource(it) }
            tvTitle.text = title
            tvDate.text = view.context.getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            tvRunningTime.text =
                view.context.getString(R.string.movie_running_time, runningTime)
        }
    }
}

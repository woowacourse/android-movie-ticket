package woowacourse.movie.movielist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.DateFormatter
import woowacourse.movie.R
import woowacourse.movie.movie.Movie

class MovieRecyclerViewHolder(
    private val view: View,
    private val listener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(view) {

    val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    val title: TextView = view.findViewById(R.id.tv_movie_title)
    val screeningPeriod: TextView = view.findViewById(R.id.tv_movie_screening_period)
    val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    val bookButton: Button = view.findViewById(R.id.bt_book_now)

    init {
        bookButton.setOnClickListener {
            listener(bindingAdapterPosition)
        }
    }

    fun bind(movieData: Movie) {
        poster.setImageResource(movieData.poster)
        title.text = movieData.title
        screeningPeriod.text = view.context.getString(
            R.string.movie_screening_period,
            DateFormatter.format(movieData.startDate),
            DateFormatter.format(movieData.endDate)
        )
        runningTime.text =
            view.context.getString(R.string.movie_running_time, movieData.runningTime)
    }
}

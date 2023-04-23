package woowacourse.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.DateFormatter
import woowacourse.movie.Movie
import woowacourse.movie.OnClickListener
import woowacourse.movie.R

class MovieRecyclerViewHolder(
    private val view: View,
    private val listener: OnClickListener<Movie>
) :
    RecyclerView.ViewHolder(view) {

    val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    val title: TextView = view.findViewById(R.id.tv_movie_title)
    val screeningPeriod: TextView = view.findViewById(R.id.tv_movie_screening_period)
    val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    val bookButton: Button = view.findViewById(R.id.bt_book_now)

    fun bind(movieData: Movie) {
        poster.setImageResource(movieData.poster)
        title.text = movieData.title
        screeningPeriod.text = view.context.getString(R.string.movie_screening_period)
            .format(
                DateFormatter.format(movieData.startDate),
                DateFormatter.format(movieData.endDate)
            )
        runningTime.text =
            view.context.getString(R.string.movie_running_time).format(movieData.runningTime)

        bookButton.setOnClickListener {
            listener.onClick(movieData)
        }
    }
}

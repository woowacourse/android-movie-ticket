package woowacourse.movie.view.movielist

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.viewmodel.MovieUIModel
import java.time.format.DateTimeFormatter

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val moviePosterView: ImageView = view.findViewById(R.id.movie_poster)
    private val movieTitleView: TextView = view.findViewById(R.id.movie_title)
    private val screeningDateView: TextView = view.findViewById(R.id.movieList_screening_date)
    private val runningTimeView: TextView = view.findViewById(R.id.movieList_running_time)
    val bookBtn: Button = view.findViewById(R.id.book_button)

    private val context: Context = moviePosterView.context

    fun bind(item: MovieUIModel) {
        with(item) {
            moviePosterView.setImageResource(moviePoster)
            movieTitleView.text = title
            val startDate = startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
            val endDate = endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
            screeningDateView.text = context.getString(R.string.screen_date, startDate, endDate)
            runningTimeView.text = context.getString(R.string.running_time, item.runningTime)
        }
    }
}

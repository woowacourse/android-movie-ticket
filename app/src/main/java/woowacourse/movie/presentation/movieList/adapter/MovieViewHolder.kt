package woowacourse.movie.presentation.movieList.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.movieList.MovieListClickListener
import woowacourse.movie.utils.formatMovieDate

class MovieViewHolder(view: View, private val listener: MovieListClickListener) :
    RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.tv_title)
    private val date = view.findViewById<TextView>(R.id.tv_date)
    private val thumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)
    private val runningTime = view.findViewById<TextView>(R.id.tv_running_time)
    private val ticketingButton = view.findViewById<Button>(R.id.btn_ticketing)

    fun bind(movie: Movie) {
        title.text = movie.title
        date.text =
            date.context.getString(
                R.string.title_date,
                formatMovieDate(movie.screeningDates.startDate),
                formatMovieDate(movie.screeningDates.endDate),
            )
        runningTime.text =
            runningTime.context.getString(R.string.title_running_time, movie.runningTime)
        thumbnail.setImageResource(movie.thumbnail)
        ticketingButton.setOnClickListener {
            listener.ticketingButtonClick(movie.id)
        }
    }
}

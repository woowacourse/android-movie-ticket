import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.movielist.OnClickListener
import java.time.format.DateTimeFormatter

class MovieViewHolder(private val view: View, private val listener: OnClickListener<MovieDto>) :
    RecyclerView.ViewHolder(view) {
    val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val movieDate: TextView = itemView.findViewById(R.id.movie_date)
    val runningTime: TextView = itemView.findViewById(R.id.running_time)
    val bookButton: Button = itemView.findViewById(R.id.book_button)

    fun bind(movie: MovieDto) {
        moviePoster.setImageResource(movie.moviePoster)
        movieTitle.text = movie.title
        movieDate.text = formatMovieRunningDate(movie)
        runningTime.text = formatMovieRunningTime(movie)

        bookButton.setOnClickListener {
            listener.onClick(movie)
        }
    }

    private fun formatMovieRunningDate(item: MovieDto): String {
        val startDate =
            item.startDate.format(DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format)))
        val endDate =
            item.endDate.format(DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format)))
        return view.context.getString(R.string.movie_running_date, startDate, endDate)
    }

    private fun formatMovieRunningTime(item: MovieDto): String {
        return view.context.getString(R.string.movie_running_time).format(item.runningTime)
    }
}

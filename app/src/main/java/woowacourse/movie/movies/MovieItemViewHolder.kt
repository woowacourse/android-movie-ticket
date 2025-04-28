package woowacourse.movie.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class MovieItemViewHolder(
    view: View,
    onBookingClick: (MovieUiModel) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.tv_movie_title)
    private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    private val date: TextView = view.findViewById(R.id.tv_movie_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val bookingBtn: Button = view.findViewById(R.id.btn_movie_booking)
    private var movie: MovieUiModel? = null

    fun bind(movie: MovieUiModel) {
        title.text = movie.title
        poster.setImageResource(movie.posterResId)
        date.text = movie.periodText()
        runningTime.text = movie.runningTimeText
        this.movie = movie
    }

    init {
        bookingBtn.setOnClickListener {
            movie?.let { movie -> onBookingClick(movie) }
        }
    }
}

package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.movie.MovieClickListener

class MovieViewHolder(
    view: View,
    private val clickListener: MovieClickListener,
) : RecyclerView.ViewHolder(view) {
    private val titleTextView: TextView = view.findViewById(R.id.tv_movie_title)
    private val posterImageView: ImageView = view.findViewById(R.id.iv_movie_poster)
    private val screeningDateTextView: TextView =
        view.findViewById(R.id.tv_movie_screening_date)
    private val runningTimeTextView: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val reservationButton: Button = view.findViewById(R.id.btn_movie_reservation)

    fun bind(movie: Movie) {
        titleTextView.text = movie.title
        posterImageView.setImageResource(movie.poster)
        screeningDateTextView.text =
            itemView.context.getString(
                R.string.movie_screening_date,
                ReservationUiFormatter.localDateToUI(movie.startDate),
                ReservationUiFormatter.localDateToUI(movie.endDate),
            )
        runningTimeTextView.text =
            itemView.context.getString(R.string.movie_running_time, movie.runningTime)
        reservationButton.setOnClickListener { clickListener.onReservationClick(movie) }
    }
}

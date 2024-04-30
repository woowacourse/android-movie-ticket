package woowacourse.movie.main.view.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
    private val title: TextView = itemView.findViewById(R.id.movieTitle)
    private val startDate: TextView = itemView.findViewById(R.id.movieStartDate)
    private val endDate: TextView = itemView.findViewById(R.id.movieEndDate)
    private val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
    private val reservationButton: Button = itemView.findViewById(R.id.movieReservationBtn)

    fun bind(
        movie: Movie,
        onReservationButtonClick: (Long) -> Unit,
    ) {
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        startDate.text = movie.date.startLocalDate.toString()
        endDate.text = movie.date.endLocalDate.toString()
        runningTime.text = movie.runningTime.toString()
        reservationButton.setOnClickListener {
            onReservationButtonClick(movie.id)
        }
    }
}

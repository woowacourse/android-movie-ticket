package woowacourse.movie.feature.movies.view.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.model.MovieUiModel

internal class MovieViewHolder(
    private val view: View,
) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.tv_movie_title)
    private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    private val date: TextView = view.findViewById(R.id.tv_movie_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val bookingButton: Button = view.findViewById(R.id.btn_movie_booking)

    fun bind(
        movie: MovieItem.Movie,
        onBookingClick: (MovieUiModel) -> Unit,
    ) {
        title.text = movie.value.title
        poster.setImageResource(movie.value.poster)

        date.text =
            view.context.getString(
                R.string.movies_movie_date_with_tilde,
                movie.value.startDate,
                movie.value.endDate,
            )

        runningTime.text =
            view.context.getString(
                R.string.movies_movie_running_time,
                movie.value.runningTime,
            )

        bookingButton.setOnClickListener { onBookingClick(movie.value) }
    }
}

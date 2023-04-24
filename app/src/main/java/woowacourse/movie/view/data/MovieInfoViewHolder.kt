package woowacourse.movie.view.data

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.widget.MovieController
import woowacourse.movie.view.widget.MovieView

class MovieInfoViewHolder(private val view: View, onClickItem: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    private val movieView: MovieView = MovieView(
        poster = view.findViewById(R.id.item_movie_poster),
        title = view.findViewById(R.id.item_movie_title),
        date = view.findViewById(R.id.item_movie_date),
        runningTime = view.findViewById(R.id.item_movie_running_time)
    )

    private val reservation: Button = view.findViewById(R.id.item_movie_reservation_button)

    init {
        reservation.setOnClickListener {
            onClickItem(adapterPosition)
        }
    }

    fun bind(movieViewData: MovieViewData) {
        MovieController.bind(movieViewData, movieView)
    }
}
